package ru.job4j.fin;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created on 11.10.17.
 * Parser.
 * @author Wamdue
 * @version 1.0
 */
public class LoadData {
    /**
     * Logger.
     */
    private static final Logger LOG = Logger.getLogger(LoadData.class);
    /**
     * List of months.
     */
    private Map<String, Integer> months = new HashMap<>();
    /**
     * List of entries.
     */
    private List<Entry> entries = new ArrayList<>();
    /**
     * Pattern of search word.
     */
    private Pattern pattern = Pattern.compile(".*[jJ][aA][vV][aA].*");
    /**
     * Pattern of exclude word.
     */
    private Pattern scriptPattern = Pattern.compile(".*[sS]cript.*");
    /**
     * Current locale.
     */
    private Locale locale = new Locale("ru_Ru");
    /**
     * Get calendar instance.
     */
    private Calendar nextStart = Calendar.getInstance();
    /**
     * Working mode.
     */
    private char mode;
    /**
     * Waiting time.
     */
    private int waitTime;
    /**
     * Status.
     */
    private boolean isWorking = true;

    /**
     * Fill internal map, for converting site date format.
     */
    {
        months.put("янв", Calendar.JANUARY);
        months.put("фев", Calendar.FEBRUARY);
        months.put("мар", Calendar.MARCH);
        months.put("апр", Calendar.APRIL);
        months.put("май", Calendar.MAY);
        months.put("июн", Calendar.JUNE);
        months.put("июл", Calendar.JULY);
        months.put("авг", Calendar.AUGUST);
        months.put("сен", Calendar.SEPTEMBER);
        months.put("окт", Calendar.OCTOBER);
        months.put("ноя", Calendar.NOVEMBER);
        months.put("дек", Calendar.DECEMBER);
        months.put("сегодня", Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        months.put("вчера", Calendar.getInstance().get(Calendar.DAY_OF_MONTH) - 1);
    }


    /**
     * Main constructor.
     * @param mode - launch frequency, M - minute, H - hour, D - day
     * @param waitTime - delay time.
     */
    public LoadData(char mode, int waitTime) {
        this.mode = mode;
        this.waitTime = waitTime;
        this.nextStart();
    }

    /**
     * Main method to add new records to DB.
     */
    private void fillList() {
        int count = 1;
        Document doc;
        Connect connect = new Connect();
        Calendar calendar = Calendar.getInstance(this.locale);
        connect.connect();

        if (connect.emptyDB()) {
            calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
        } else {
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 1);
        }
        LOG.info("Starting to parse: " + this.nextStart.getTime());
        while (true) {
            try {
                doc = Jsoup.connect("http://www.sql.ru/forum/job-offers/" + count).get();
                Elements el = doc.select("table.forumTable > tbody > tr");
                Calendar temp = null;
                for (Element e : el) {
                    Element name = e.select("td.postslisttopic").first();
                    Element time = e.select("td.altCol").last();
                    if (name != null && time != null) {
                        Element url = name.getElementsByTag("a").first();
                        temp = this.getTime(time.text());
                        if (calendar.compareTo(temp) < 0) {
                            this.addToList(name.text(), url.attr("href"), temp);
                        }
                    }
                }
                if (temp != null && temp.compareTo(calendar) < 0) {
                    break;
                }
                count++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        connect.addBatch(entries);
        connect.close();
    }

    /**
     * Add new record to array.
     * @param name - name of vacancy.
     * @param url - url of the topic.
     * @param date - date of post.
     */
    private void addToList(String name, String url, Calendar date) {
        if (pattern.matcher(name).matches() && !scriptPattern.matcher(name).matches()) {
            Entry entry = new Entry();
            entry.setSource("www.sql.ru");
            entry.setName(name);
            entry.setUrl(url);
            entry.setTimestamp(new Timestamp(date.getTimeInMillis()));
            entries.add(entry);
        }
    }

    /**
     * Parse string to create Timestamp.
     *
     * @param value - string with date / time information.
     * @return actual Timestamp.
     */
    private Calendar getTime(String value) {
        String[] dateTime = value.split(", ");
        Calendar cal = Calendar.getInstance(this.locale);
        if (months.containsKey(dateTime[0])) {
            cal.set(Calendar.DAY_OF_MONTH, months.get(dateTime[0]));
        } else {
            String[] date = dateTime[0].split(" ");
            String[] time = dateTime[1].split(":");
            cal.set(Calendar.DAY_OF_MONTH, Integer.valueOf(date[0]));
            cal.set(Calendar.MONTH, months.get(date[1]));
            cal.set(Calendar.YEAR, 2000 + Integer.valueOf(date[2]));
            cal.set(Calendar.HOUR, Integer.valueOf(time[0]));
            cal.set(Calendar.MINUTE, Integer.valueOf(time[1]));
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
        }
        return cal;
    }

    /**
     * Start main cycle.
     */
    public void start() {
        this.fillList();
        while (isWorking) {
            Calendar currentTime = Calendar.getInstance(this.locale);
            if (this.nextStart.getTime().compareTo(currentTime.getTime()) > 0) {
                try {

                    Thread.sleep(this.nextStart.getTimeInMillis() - currentTime.getTimeInMillis());
                    continue;
                } catch (InterruptedException e) {
                    LOG.error("Interrupted sleep time", e.fillInStackTrace());
                    e.printStackTrace();
                }
            }
            this.nextStart();
            this.fillList();
        }
    }

    /**
     * Stop cycle.
     */
    public void stop() {
        this.isWorking = false;
    }

    /**
     * Set new start time.
     */
    private void nextStart() {
        if (mode == 'M') {
            this.nextStart.set(Calendar.MINUTE, this.nextStart.get(Calendar.MINUTE) + this.waitTime);
        } else if (mode == 'H') {
            this.nextStart.set(Calendar.HOUR, this.nextStart.get(Calendar.HOUR) + this.waitTime);
        } else if (mode == 'D') {
            this.nextStart.set(Calendar.DAY_OF_MONTH, this.nextStart.get(Calendar.DAY_OF_MONTH) + this.waitTime);
        }
        LOG.info("Next time, will start at: " + nextStart.getTime());
    }

    /**
     * Start method.
     * @param args - not in use.
     */
    public static void main(String[] args) {
        new LoadData('M', 1).start();
    }


}

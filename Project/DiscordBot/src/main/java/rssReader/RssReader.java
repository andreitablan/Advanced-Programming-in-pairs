package rssReader;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.io.IOException;
import java.net.URL;

public class RssReader {


    public RssReader() throws IOException, FeedException {
    }

    public String readRss(String link) throws IOException, FeedException {
        String answer = "";
        URL feedSource = new URL(link);
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(feedSource));
        answer = feed.getTitle() + '\n' + feed.getDescription();
        return answer;
    }
}
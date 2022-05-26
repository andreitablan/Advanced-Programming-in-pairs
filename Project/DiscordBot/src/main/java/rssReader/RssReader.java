package rssReader;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class RssReader {

    public RssReader() throws MalformedURLException {
    }

    public void readRss() throws IOException, FeedException {
        URL feedSource =  new URL("https://feeds.simplecast.com/54nAGcIl");
        SyndFeedInput input=new SyndFeedInput();
        SyndFeed feed= input.build(new XmlReader(feedSource));
        System.out.println(feed);
    }
}

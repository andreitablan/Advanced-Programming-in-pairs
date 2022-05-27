package rssReader;

import com.sun.syndication.feed.synd.*;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.SyndFeedOutput;
import com.sun.syndication.io.XmlReader;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RssReader {


    public RssReader() throws IOException, FeedException {
    }

    public String readRss(String link) throws IOException, FeedException {
        String answer="";
        URL feedSource =  new URL(link);
        SyndFeedInput input=new SyndFeedInput();
        SyndFeed feed= input.build(new XmlReader(feedSource));
        answer=feed.getTitle()+'\n'+feed.getDescription();
        return answer;
    }
}
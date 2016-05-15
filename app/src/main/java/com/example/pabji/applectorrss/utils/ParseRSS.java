package com.example.pabji.applectorrss.utils;

import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;
import android.sax.RootElement;
import android.sax.StartElementListener;
import android.util.Xml;

import com.example.pabji.applectorrss.models.Item;

import org.xml.sax.Attributes;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pabji on 14/05/2016.
 */
public class ParseRSS {
    private URL urlRSS;
    private Item currentItem;

    public ParseRSS(String url) {
        try {
            this.urlRSS = new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Item> parse() {
        final List<Item> items = new ArrayList<Item>();

        RootElement root = new RootElement("rss");
        Element item = root.getChild("channel").getChild("item");

        item.setStartElementListener(new StartElementListener() {
            public void start(Attributes attrs) {
                currentItem = new Item();
            }
        });

        item.setEndElementListener(new EndElementListener() {
            public void end() {
                items.add(currentItem);
            }
        });

        item.getChild("title").setEndTextElementListener(
                new EndTextElementListener() {
                    public void end(String body) {
                        currentItem.setTitle(body);
                    }
                });
        item.getChild("link").setEndTextElementListener(
                new EndTextElementListener() {
                    @Override
                    public void end(String body) {
                        currentItem.setLink(body);
                    }
                });
        item.getChild("description").setEndTextElementListener(
                new EndTextElementListener() {
                    @Override
                    public void end(String body) {
                        currentItem.setDescription(body);
                    }
                }
        );

        item.getChild("pubDate").setEndTextElementListener(
                new EndTextElementListener() {
                    @Override
                    public void end(String body) {
                        currentItem.setPubDate(body);
                    }
                }
        );

        item.getChild("content:encoded").setEndTextElementListener(
                new EndTextElementListener() {
                    @Override
                    public void end(String body) {
                        currentItem.setContent(body);
                    }
                }
        );

        item.getChild("enclosure").setStartElementListener(
                new StartElementListener() {
                    @Override
                    public void start(Attributes attributes) {
                        String urlImage = attributes.getValue("url");
                        currentItem.setImageUrl(urlImage);
                    }
                }
        );

        try {
            Xml.parse(this.getInputStream(), Xml.Encoding.UTF_8, root.getContentHandler());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return items;
    }

    private InputStream getInputStream() {
        try {
            return urlRSS.openConnection().getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

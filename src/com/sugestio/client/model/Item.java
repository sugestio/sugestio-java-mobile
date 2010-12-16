/**
 * The MIT License
 * 
 * Copyright (c) 2010 Sugestio
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.sugestio.client.model;

import java.util.ArrayList;
import java.util.List;

public class Item {

	private String id;
    private String title;
    private String permalink;
    private String available;
    private String description_short;
    private String description_long;
    private String from;
    private String until;
    private String location_simple;
    private String location_latlong;
    private List<String> category = new ArrayList<String>();
    private List<String> creator = new ArrayList<String>(); 
    private List<String> segment = new ArrayList<String>();
    private List<String> tag = new ArrayList<String>();
    
    
    public Item() {
    	
    }
    
    /**
     * Creates a new Item
     * @param id string that uniquely identifies the item
     */
    public Item(String id) {
    	this.id = id;
    }

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the permalink
	 */
	public String getPermalink() {
		return permalink;
	}

	/**
	 * @param permalink the permalink to set
	 */
	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	/**
	 * @return the available
	 */
	public String getAvailable() {
		return available;
	}

	/**
	 * @param available the available to set
	 */
	public void setAvailable(String available) {
		this.available = available;
	}

	/**
	 * @return the description_short
	 */
	public String getDescription_short() {
		return description_short;
	}

	/**
	 * @param description_short the description_short to set
	 */
	public void setDescription_short(String description_short) {
		this.description_short = description_short;
	}

	/**
	 * @return the description_long
	 */
	public String getDescription_long() {
		return description_long;
	}

	/**
	 * @param description_long the description_long to set
	 */
	public void setDescription_long(String description_long) {
		this.description_long = description_long;
	}

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * @return the until
	 */
	public String getUntil() {
		return until;
	}

	/**
	 * @param until the until to set
	 */
	public void setUntil(String until) {
		this.until = until;
	}

	/**
	 * @return the location_simple
	 */
	public String getLocation_simple() {
		return location_simple;
	}

	/**
	 * @param location_simple the location_simple to set
	 */
	public void setLocation_simple(String location_simple) {
		this.location_simple = location_simple;
	}

	/**
	 * @return the location_latlong
	 */
	public String getLocation_latlong() {
		return location_latlong;
	}

	/**
	 * @param location_latlong the location_latlong to set
	 */
	public void setLocation_latlong(String location_latlong) {
		this.location_latlong = location_latlong;
	}

	/**
	 * @return the creator
	 */
	public List<String> getCreator() {
		return creator;
	}

	/**
	 * @param creator the creator to set
	 */
	public void setCreator(List<String> creator) {
		this.creator = creator;
	}

	/**
	 * @return the tag
	 */
	public List<String> getTag() {
		return tag;
	}

	/**
	 * @param tag the tag to set
	 */
	public void setTag(List<String> tag) {
		this.tag = tag;
	}

	/**
	 * @return the category
	 */
	public List<String> getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(List<String> category) {
		this.category = category;
	}

	/**
	 * @return the segment
	 */
	public List<String> getSegment() {
		return segment;
	}

	/**
	 * @param segment the segment to set
	 */
	public void setSegment(List<String> segment) {
		this.segment = segment;
	}
    
	public void addCategory(String category) {
        this.category.add(category);
    }
	
	public void addCreator(String creator) {
        this.creator.add(creator);
    }	
	    
	public void addSegment(String segment) {
        this.segment.add(segment);
    }
	
	public void addTag(String tag) {
        this.tag.add(tag);
    }
}

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


public class Recommendation {

	private String itemid;
	private String score;
	private String algorithm;
	private String certainty;
	private Item item;
	
	
	public Recommendation() {
				
	}	

	/**
	 * @param certainty the certainty to set
	 */
	public void setCertainty(String certainty) {
		this.certainty = certainty;
	}

	/**
	 * @return the certainty
	 */
	public String getCertainty() {
		return certainty;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(String score) {
		this.score = score;
	}

	/**
	 * @return the score
	 */
	public String getScore() {
		return score;
	}



	/**
	 * @param itemid the itemid to set
	 */
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}



	/**
	 * @return the itemid
	 */
	public String getItemid() {
		return itemid;
	}



	/**
	 * @param algorithm the algorithm to set
	 */
	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}



	/**
	 * @return the algorithm
	 */
	public String getAlgorithm() {
		return algorithm;
	}

	/**
	 * @param item the item metadata to set
	 */
	public void setItem(Item item) {
		this.item = item;
	}

	/**
	 * @return the item metadata
	 */
	public Item getItem() {
		return item;
	}
	
}

/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.pinot.core.segment.virtualcolumn;

import java.io.IOException;
import org.apache.pinot.core.segment.index.column.ColumnIndexContainer;
import org.apache.pinot.core.segment.index.readers.BloomFilterReader;
import org.apache.pinot.core.segment.index.readers.Dictionary;
import org.apache.pinot.core.segment.index.readers.ForwardIndexReader;
import org.apache.pinot.core.segment.index.readers.InvertedIndexReader;
import org.apache.pinot.core.segment.index.readers.JsonIndexReader;
import org.apache.pinot.core.segment.index.readers.NullValueVectorReaderImpl;
import org.apache.pinot.core.segment.index.readers.TextIndexReader;


/**
 * Column index container for virtual columns.
 */
public class VirtualColumnIndexContainer implements ColumnIndexContainer {
  private final ForwardIndexReader<?> _forwardIndex;
  private final InvertedIndexReader<?> _invertedIndex;
  private final Dictionary _dictionary;

  public VirtualColumnIndexContainer(ForwardIndexReader<?> forwardIndex, InvertedIndexReader<?> invertedIndex,
      Dictionary dictionary) {
    _forwardIndex = forwardIndex;
    _invertedIndex = invertedIndex;
    _dictionary = dictionary;
  }

  @Override
  public ForwardIndexReader<?> getForwardIndex() {
    return _forwardIndex;
  }

  @Override
  public InvertedIndexReader<?> getInvertedIndex() {
    return _invertedIndex;
  }

  @Override
  public InvertedIndexReader<?> getRangeIndex() {
    return null;
  }

  @Override
  public TextIndexReader getTextIndex() {
    return null;
  }

  @Override
  public TextIndexReader getFSTIndex() {
    return null;
  }

  @Override
  public JsonIndexReader getJsonIndex() {
    return null;
  }

  @Override
  public Dictionary getDictionary() {
    return _dictionary;
  }

  @Override
  public BloomFilterReader getBloomFilter() {
    return null;
  }

  @Override
  public NullValueVectorReaderImpl getNullValueVector() {
    return null;
  }

  @Override
  public void close()
      throws IOException {
    _forwardIndex.close();
    if (_invertedIndex != null) {
      _invertedIndex.close();
    }
    if (_dictionary != null) {
      _dictionary.close();
    }
  }
}

/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package extractor.src.main.java.com.google.android.exoplayer2.extractor.mkv;

import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import java.io.IOException;

/**
 * Event-driven EBML reader that delivers events to an {@link EbmlProcessor}.
 *
 * <p>EBML can be summarized as a binary XML format somewhat similar to Protocol Buffers. It was
 * originally designed for the Matroska container format. More information about EBML and Matroska
 * is available <a href="http://www.matroska.org/technical/specs/index.html">here</a>.
 *
 * @deprecated com.google.android.exoplayer2 is deprecated. Please migrate to androidx.media3 (which
 *     contains the same ExoPlayer code). See <a
 *     href="https://developer.android.com/guide/topics/media/media3/getting-started/migration-guide">the
 *     migration guide</a> for more details, including a script to help with the migration.
 */
@Deprecated
/* package */ interface EbmlReader {

  /**
   * Initializes the extractor with an {@link EbmlProcessor}.
   *
   * @param processor An {@link EbmlProcessor} to process events.
   */
  void init(EbmlProcessor processor);

  /**
   * Resets the state of the reader.
   *
   * <p>Subsequent calls to {@link #read(ExtractorInput)} will start reading a new EBML structure
   * from scratch.
   */
  void reset();

  /**
   * Reads from an {@link ExtractorInput}, invoking an event callback if possible.
   *
   * @param input The {@link ExtractorInput} from which data should be read.
   * @return True if data can continue to be read. False if the end of the input was encountered.
   * @throws ParserException If parsing fails.
   * @throws IOException If an error occurs reading from the input.
   */
  boolean read(ExtractorInput input) throws IOException;
}

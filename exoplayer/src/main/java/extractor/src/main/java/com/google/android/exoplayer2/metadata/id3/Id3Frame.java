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
package extractor.src.main.java.com.google.android.exoplayer2.metadata.id3;

import com.google.android.exoplayer2.metadata.Metadata;

/**
 * Base class for ID3 frames.
 *
 * @deprecated com.google.android.exoplayer2 is deprecated. Please migrate to androidx.media3 (which
 *     contains the same ExoPlayer code). See <a
 *     href="https://developer.android.com/guide/topics/media/media3/getting-started/migration-guide">the
 *     migration guide</a> for more details, including a script to help with the migration.
 */
@Deprecated
public abstract class Id3Frame implements Metadata.Entry {

  /** The frame ID. */
  public final String id;

  public Id3Frame(String id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return id;
  }

  @Override
  public int describeContents() {
    return 0;
  }
}

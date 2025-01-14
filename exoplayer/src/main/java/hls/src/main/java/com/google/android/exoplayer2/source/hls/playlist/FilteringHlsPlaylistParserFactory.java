/*
 * Copyright (C) 2018 The Android Open Source Project
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
package hls.src.main.java.com.google.android.exoplayer2.source.hls.playlist;

import androidx.annotation.Nullable;
import com.google.android.exoplayer2.offline.FilteringManifestParser;
import com.google.android.exoplayer2.offline.StreamKey;
import com.google.android.exoplayer2.upstream.ParsingLoadable;
import java.util.List;

/**
 * A {@link HlsPlaylistParserFactory} that includes only the streams identified by the given stream
 * keys.
 *
 * @deprecated com.google.android.exoplayer2 is deprecated. Please migrate to androidx.media3 (which
 *     contains the same ExoPlayer code). See <a
 *     href="https://developer.android.com/guide/topics/media/media3/getting-started/migration-guide">the
 *     migration guide</a> for more details, including a script to help with the migration.
 */
@Deprecated
public final class FilteringHlsPlaylistParserFactory implements HlsPlaylistParserFactory {

  private final HlsPlaylistParserFactory hlsPlaylistParserFactory;
  private final List<StreamKey> streamKeys;

  /**
   * @param hlsPlaylistParserFactory A factory for the parsers of the playlists which will be
   *     filtered.
   * @param streamKeys The stream keys. If null or empty then filtering will not occur.
   */
  public FilteringHlsPlaylistParserFactory(
      HlsPlaylistParserFactory hlsPlaylistParserFactory, List<StreamKey> streamKeys) {
    this.hlsPlaylistParserFactory = hlsPlaylistParserFactory;
    this.streamKeys = streamKeys;
  }

  @Override
  public ParsingLoadable.Parser<HlsPlaylist> createPlaylistParser() {
    return new FilteringManifestParser<>(
        hlsPlaylistParserFactory.createPlaylistParser(), streamKeys);
  }

  @Override
  public ParsingLoadable.Parser<HlsPlaylist> createPlaylistParser(
      HlsMultivariantPlaylist multivariantPlaylist,
      @Nullable HlsMediaPlaylist previousMediaPlaylist) {
    return new FilteringManifestParser<>(
        hlsPlaylistParserFactory.createPlaylistParser(multivariantPlaylist, previousMediaPlaylist),
        streamKeys);
  }
}

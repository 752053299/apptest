/*
 * Copyright 2022 The Android Open Source Project
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

package effect.src.main.java.com.google.android.exoplayer2.effect;

import android.content.Context;
import com.google.android.exoplayer2.util.GlUtil;
import com.google.android.exoplayer2.util.VideoFrameProcessingException;

/**
 * Specifies color transformations using color lookup tables to apply to each frame in the fragment
 * shader.
 *
 * @deprecated com.google.android.exoplayer2 is deprecated. Please migrate to androidx.media3 (which
 *     contains the same ExoPlayer code). See <a
 *     href="https://developer.android.com/guide/topics/media/media3/getting-started/migration-guide">the
 *     migration guide</a> for more details, including a script to help with the migration.
 */
@Deprecated
public interface ColorLut extends GlEffect {

  /**
   * Returns the OpenGL texture ID of the LUT to apply to the pixels of the frame with the given
   * timestamp.
   */
  int getLutTextureId(long presentationTimeUs);

  /** Returns the length N of the 3D N x N x N LUT cube with the given timestamp. */
  int getLength(long presentationTimeUs);

  /** Releases the OpenGL texture of the LUT. */
  void release() throws GlUtil.GlException;

  @Override
  default SingleFrameGlShaderProgram toGlShaderProgram(Context context, boolean useHdr)
      throws VideoFrameProcessingException {
    return new ColorLutShaderProgram(context, /* colorLut= */ this, useHdr);
  }
}

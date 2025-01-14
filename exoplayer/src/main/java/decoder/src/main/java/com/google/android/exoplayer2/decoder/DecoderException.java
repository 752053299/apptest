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
package decoder.src.main.java.com.google.android.exoplayer2.decoder;

import androidx.annotation.Nullable;

/**
 * Thrown when a {@link Decoder} error occurs.
 *
 * @deprecated com.google.android.exoplayer2 is deprecated. Please migrate to androidx.media3 (which
 *     contains the same ExoPlayer code). See <a
 *     href="https://developer.android.com/guide/topics/media/media3/getting-started/migration-guide">the
 *     migration guide</a> for more details, including a script to help with the migration.
 */
@Deprecated
public class DecoderException extends Exception {

  /**
   * Creates an instance.
   *
   * @param message The detail message for this exception.
   */
  public DecoderException(String message) {
    super(message);
  }

  /**
   * Creates an instance.
   *
   * @param cause The cause of this exception, or {@code null}.
   */
  public DecoderException(@Nullable Throwable cause) {
    super(cause);
  }

  /**
   * Creates an instance.
   *
   * @param message The detail message for this exception.
   * @param cause The cause of this exception, or {@code null}.
   */
  public DecoderException(String message, @Nullable Throwable cause) {
    super(message, cause);
  }
}

/*
 * Copyright 2021 The Android Open Source Project
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
package extractor.src.main.java.com.google.android.exoplayer2.text;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.BundleableUtil;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;

/**
 * Decodes data encoded by {@link CueEncoder}.
 *
 * @deprecated com.google.android.exoplayer2 is deprecated. Please migrate to androidx.media3 (which
 *     contains the same ExoPlayer code). See <a
 *     href="https://developer.android.com/guide/topics/media/media3/getting-started/migration-guide">the
 *     migration guide</a> for more details, including a script to help with the migration.
 */
@Deprecated
public final class CueDecoder {

  // key under which list of cues is saved in the bundle
  static final String BUNDLED_CUES = "c";

  /**
   * Decodes byte array into list of {@link Cue} objects.
   *
   * @param bytes byte array produced by {@link CueEncoder}
   * @return decoded list of {@link Cue} objects.
   */
  public ImmutableList<Cue> decode(byte[] bytes) {
    Parcel parcel = Parcel.obtain();
    parcel.unmarshall(bytes, 0, bytes.length);
    parcel.setDataPosition(0);
    Bundle bundle = parcel.readBundle(Bundle.class.getClassLoader());
    parcel.recycle();
    ArrayList<Bundle> bundledCues =
        Assertions.checkNotNull(bundle.getParcelableArrayList(BUNDLED_CUES));

    return BundleableUtil.fromBundleList(Cue.CREATOR, bundledCues);
  }
}

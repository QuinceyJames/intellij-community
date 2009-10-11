/*
 * Copyright 2000-2009 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.intellij.history.core.revisions;

import com.intellij.history.core.LocalVcsTestCase;
import com.intellij.history.core.changes.Change;
import com.intellij.history.core.changes.ChangeList;
import com.intellij.history.core.changes.CreateFileChange;
import com.intellij.history.core.tree.Entry;
import com.intellij.history.core.tree.RootEntry;
import org.junit.Test;

public class RevisionsAfterChangeTest extends LocalVcsTestCase {
  @Test
  public void testForRootEntry() {
    Entry root = new RootEntry();
    ChangeList list = new ChangeList();
    Change c1 = new CreateFileChange(1, "f1", null, -1, false);
    Change c2 = new CreateFileChange(2, "f2", null, -1, false);

    c1.applyTo(root);
    c2.applyTo(root);
    list.addChange(c1);
    list.addChange(c2);

    Revision r = new RevisionAfterChange(root, root, list, c1);
    Entry e = r.getEntry();

    assertEquals(e.getClass(), RootEntry.class);
    assertNotNull(e.findEntry("f1"));
    assertNull(e.findEntry("f2"));
  }
}

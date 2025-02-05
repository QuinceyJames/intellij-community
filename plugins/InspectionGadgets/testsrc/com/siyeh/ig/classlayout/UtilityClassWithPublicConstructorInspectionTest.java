// Copyright 2000-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.siyeh.ig.classlayout;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.testFramework.LightProjectDescriptor;
import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase;
import com.siyeh.ig.LightJavaInspectionTestCase;
import org.jetbrains.annotations.NotNull;

public class UtilityClassWithPublicConstructorInspectionTest extends LightJavaCodeInsightFixtureTestCase {
  @Override
  protected String getBasePath() {
    return LightJavaInspectionTestCase.INSPECTION_GADGETS_TEST_DATA_PATH + "com/siyeh/igtest/classlayout/utility_class_with_public_constructor";
  }

  @NotNull
  @Override
  protected LightProjectDescriptor getProjectDescriptor() {
    return JAVA_8;
  }

  private void doTest() {
    myFixture.enableInspections(new UtilityClassWithPublicConstructorInspection());
    myFixture.testHighlighting(getTestName(false) + ".java");
  }

  public void testUtilityClassWithPublicConstructorInspection() {
    doTest();
  }
  public void testUtilityClassWithInheritor() {
    doTest();
  }

  public void testQuickfix() {
    doTest();
    final IntentionAction intention = myFixture.getAvailableIntention("Fix all 'Utility class with 'public' constructor' problems in file");
    assertNotNull(intention);
    myFixture.launchAction(intention);
    myFixture.checkResultByFile(getTestName(false) + ".after.java");
  }
}

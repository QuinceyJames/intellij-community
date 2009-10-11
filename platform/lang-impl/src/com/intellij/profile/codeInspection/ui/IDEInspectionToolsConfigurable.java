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

/*
 * User: anna
 * Date: 17-Apr-2009
 */
package com.intellij.profile.codeInspection.ui;

import com.intellij.codeInspection.ex.InspectionProfileImpl;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.util.Comparing;
import com.intellij.profile.codeInspection.InspectionProfileManager;
import com.intellij.profile.codeInspection.InspectionProjectProfileManager;

public class IDEInspectionToolsConfigurable extends InspectionToolsConfigurable {
  public IDEInspectionToolsConfigurable(InspectionProjectProfileManager projectProfileManager, InspectionProfileManager profileManager) {
    super(projectProfileManager, profileManager);
  }

  protected InspectionProfileImpl getCurrentProfile() {
    return (InspectionProfileImpl)((InspectionProfileManager)myProfileManager).getRootProfile();
  }

  @Override
  public void apply() throws ConfigurationException {
    super.apply();
    myProfileManager.setRootProfile(getSelectedObject().getName());
  }

  @Override
  public boolean isModified() {
    if (!Comparing.strEqual(getSelectedObject().getName(), getCurrentProfile().getName())) return true;
    return super.isModified();
  }
}
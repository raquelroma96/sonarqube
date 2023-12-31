/*
 * SonarQube
 * Copyright (C) 2009-2023 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.scanner.ci.vendors;

import javax.annotation.Nullable;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.event.Level;
import org.sonar.api.testfixtures.log.LogTester;
import org.sonar.api.utils.System2;
import org.sonar.scanner.ci.CiVendor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GithubActionsTest {

  @Rule
  public LogTester logs = new LogTester();

  private System2 system = mock(System2.class);
  private CiVendor underTest = new GithubActions(system);

  @Test
  public void getName() {
    assertThat(underTest.getName()).isEqualTo("Github Actions");
  }

  @Test
  public void isDetected() {
    setEnvVariable("GITHUB_ACTION", "build");
    assertThat(underTest.isDetected()).isTrue();

    setEnvVariable("GITHUB_ACTION", null);
    assertThat(underTest.isDetected()).isFalse();
  }

  @Test
  public void loadConfiguration() {
    setEnvVariable("GITHUB_ACTION", "build");
    setEnvVariable("GITHUB_SHA", "abd12fc");

    assertThat(underTest.loadConfiguration().getScmRevision()).hasValue("abd12fc");
  }

  @Test
  public void log_warning_if_missing_GITHUB_SHA() {
    setEnvVariable("GITHUB_ACTION", "build");

    assertThat(underTest.loadConfiguration().getScmRevision()).isEmpty();
    assertThat(logs.logs(Level.WARN)).contains("Missing environment variable GITHUB_SHA");
  }

  private void setEnvVariable(String key, @Nullable String value) {
    when(system.envVariable(key)).thenReturn(value);
  }
}

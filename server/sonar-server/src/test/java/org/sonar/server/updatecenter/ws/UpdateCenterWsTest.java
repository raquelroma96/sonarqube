/*
 * SonarQube
 * Copyright (C) 2009-2019 SonarSource SA
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
package org.sonar.server.updatecenter.ws;

import org.junit.Before;
import org.junit.Test;
import org.sonar.api.server.ws.WebService;
import org.sonar.server.ws.WsTester;

import static org.assertj.core.api.Assertions.assertThat;

public class UpdateCenterWsTest {

  WsTester tester;

  @Before
  public void setUp() {
    tester = new WsTester(new UpdateCenterWs(new InstalledPluginsAction(null)));
  }

  @Test
  public void define_controller() {
    WebService.Controller controller = tester.controller("api/updatecenter");
    assertThat(controller).isNotNull();
    assertThat(controller.since()).isEqualTo("2.10");
    assertThat(controller.description()).isNotEmpty();
    assertThat(controller.actions()).hasSize(2);
  }

  @Test
  public void define_installed_plugins_action() {
    WebService.Controller controller = tester.controller("api/updatecenter");

    WebService.Action action = controller.action("installed_plugins");
    assertThat(action).isNotNull();
    assertThat(action.handler()).isNotNull();
    assertThat(action.isInternal()).isTrue();
    assertThat(action.isPost()).isFalse();
    assertThat(action.params()).hasSize(1);
  }
}
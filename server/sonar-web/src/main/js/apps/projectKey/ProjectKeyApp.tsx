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
import * as React from 'react';
import { Helmet } from 'react-helmet-async';
import { changeKey } from '../../api/components';
import withComponentContext from '../../app/components/componentContext/withComponentContext';
import RecentHistory from '../../app/components/RecentHistory';
import { Router, withRouter } from '../../components/hoc/withRouter';
import { translate } from '../../helpers/l10n';
import { Component } from '../../types/types';
import UpdateForm from './UpdateForm';

interface Props {
  component: Component;
  router: Router;
}

function ProjectKeyApp({ component, router }: Props) {
  const handleChangeKey = (newKey: string) => {
    return changeKey({ from: component.key, to: newKey }).then(() => {
      RecentHistory.remove(component.key);
      router.replace({ pathname: '/project/key', query: { id: newKey } });
    });
  };

  return (
    <div className="page page-limited" id="project-key">
      <Helmet defer={false} title={translate('update_key.page')} />
      <header className="page-header">
        <h1 className="page-title">{translate('update_key.page')}</h1>
        <div className="page-description">{translate('update_key.page.description')}</div>
      </header>
      <UpdateForm component={component} onKeyChange={handleChangeKey} />
    </div>
  );
}

export default withComponentContext(withRouter(ProjectKeyApp));

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
import { ClipboardButton } from '../../../../../../components/controls/clipboard';
import { translate } from '../../../../../../helpers/l10n';

export interface MetaKeyProps {
  componentKey: string;
  qualifier: string;
}

export default function MetaKey({ componentKey, qualifier }: MetaKeyProps) {
  return (
    <>
      <h3 id="project-key">{translate('overview.project_key', qualifier)}</h3>
      <div className="display-flex-center">
        <input
          className="overview-key"
          aria-labelledby="project-key"
          readOnly
          type="text"
          value={componentKey}
        />
        <ClipboardButton
          aria-label={translate('overview.project_key.click_to_copy')}
          className="little-spacer-left"
          copyValue={componentKey}
        />
      </div>
    </>
  );
}

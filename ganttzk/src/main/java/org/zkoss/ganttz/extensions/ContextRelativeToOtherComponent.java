/*
 * This file is part of ###PROJECT_NAME###
 *
 * Copyright (C) 2009 Fundación para o Fomento da Calidade Industrial e
 *                    Desenvolvemento Tecnolóxico de Galicia
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.zkoss.ganttz.extensions;

import org.zkoss.ganttz.adapters.IDomainAndBeansMapper;
import org.zkoss.ganttz.adapters.PlannerConfiguration;
import org.zkoss.ganttz.data.Position;
import org.zkoss.ganttz.timetracker.TimeTracker;
import org.zkoss.zk.ui.Component;

/**
 * An implementation of {@link IContext} that delegates to another context and
 * redefines its {@link IContext#getRelativeTo()}
 * @author Óscar González Fernández <ogonzalez@igalia.com>
 */
public class ContextRelativeToOtherComponent<T> implements IContext<T> {

    private final Component component;
    private final IContext<T> context;

    public static <T> IContext<T> makeRelativeTo(IContext<T> context,
            Component component) {
        return new ContextRelativeToOtherComponent<T>(component, context);
    }

    private ContextRelativeToOtherComponent(Component component,
            IContext<T> context) {
        if (component == null) {
            throw new IllegalArgumentException("component must be not null");
        }
        if (context == null) {
            throw new IllegalArgumentException("context must be not null");
        }
        this.component = component;
        this.context = context;
    }

    public void add(T domainObject) {
        context.add(domainObject);
    }

    public Component getRelativeTo() {
        return component;
    }

    public void reload(PlannerConfiguration<?> configuration) {
        context.reload(configuration);
    }

    public Position remove(T domainObject) {
        return context.remove(domainObject);
    }

    public void add(Position position, T domainObject) {
        context.add(position, domainObject);
    };

    public void replace(T oldDomainObject, T newDomainObject) {
        context.replace(oldDomainObject, newDomainObject);
    }

    @Override
    public TimeTracker getTimeTracker() {
        return context.getTimeTracker();
    }

    @Override
    public IDomainAndBeansMapper<T> getMapper() {
        return context.getMapper();
    }

    @Override
    public void recalculatePosition(T domainObject) {
        context.recalculatePosition(domainObject);
    }

}

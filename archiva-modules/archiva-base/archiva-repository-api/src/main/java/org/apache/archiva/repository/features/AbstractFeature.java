package org.apache.archiva.repository.features;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import org.apache.archiva.repository.RepositoryEvent;
import org.apache.archiva.repository.RepositoryEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AbstractFeature {
    private List<RepositoryEventListener> listener = new ArrayList<>();

    AbstractFeature() {

    }

    AbstractFeature(RepositoryEventListener listener) {
        this.listener.add(listener);
    }

    AbstractFeature(Collection<RepositoryEventListener> listeners) {
        this.listener.addAll(listeners);
    }

    public void addListener(RepositoryEventListener listener) {
        if (!this.listener.contains(listener)) {
            this.listener.add(listener);
        }
        this.listener.add(listener);
    }

    public void removeListener(RepositoryEventListener listener) {
        this.listener.remove(listener);
    }

    public void clearListeners() {
        this.listener.clear();
    }

    protected <T> void raiseEvent(RepositoryEvent<T> event) {
        for(RepositoryEventListener listr : listener) {
            listr.raise(event);
        }
    }


}

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

package javax.mail.search;

import javax.mail.Message;

/**
 * Term that implements a logical negation.
 *
 * @version $Rev: 920714 $ $Date: 2010-03-09 07:55:49 +0100 (Di, 09. Mär 2010) $
 */
public final class NotTerm extends SearchTerm {
	
	private static final long serialVersionUID = 7152293214217310216L;
	
    protected SearchTerm term;

    public NotTerm(SearchTerm term) {
        this.term = term;
    }

    public SearchTerm getTerm() {
        return term;
    }

    public boolean match(Message message) {
        return !term.match(message);
    }

    public boolean equals(Object other) {
        if (other == this) return true;
        if (other instanceof NotTerm == false) return false;
        return term.equals(((NotTerm) other).term);
    }

    public int hashCode() {
        return term.hashCode();
    }
}

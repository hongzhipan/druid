/*
 * Copyright 1999-2101 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.druid.sql.ast;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.druid.sql.visitor.SQLASTVisitor;

public class SQLPartitionValue extends SQLObjectImpl {

    protected Operator            operator;
    protected final List<SQLExpr> items = new ArrayList<SQLExpr>();

    public SQLPartitionValue(Operator operator){
        super();
        this.operator = operator;
    }

    public List<SQLExpr> getItems() {
        return items;
    }
    
    public void addItem(SQLExpr item) {
        if (item != null) {
            item.setParent(this);
        }
        this.items.add(item);
    }

    public Operator getOperator() {
        return operator;
    }

    public static enum Operator {
                                 LessThan, //
                                 In, //
                                 List
    }

    @Override
    protected void accept0(SQLASTVisitor visitor) {
        if (visitor.visit(this)) {
            acceptChild(visitor, getItems());
        }
        visitor.endVisit(this);
    }
}

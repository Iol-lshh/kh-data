/*
 * MIT License
 *
 * Copyright (c) 2024 KH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package keyhub.data.structure.table.join.left;

import keyhub.data.structure.table.KhTable;
import keyhub.data.structure.table.join.KhTableJoinImplement;

import java.util.ArrayList;
import java.util.List;

public class KhTableLeftJoinImplement extends KhTableJoinImplement implements KhTableLeftJoin {


    public KhTableLeftJoinImplement(KhTable left, KhTable right) {
        super(left, right);
    }

    @Override
    public List<List<Object>> computeJoinRawResult(){

        List<List<Object>> rawResult = new ArrayList<>();
        for(int i = 0; i < this.left.count(); i++){
            boolean anyMatched = false;
            for(int j = 0; j < this.right.count(); j++){
                boolean isJoined = isJoinedRow(this.left.getRow(i), this.right.getRow(j));
                if(isJoined){
                    List<Object> row = new ArrayList<>();
                    row.addAll(left.getRawRow(i));
                    row.addAll(right.getRawRow(j));
                    anyMatched = true;
                    rawResult.add(row);
                }
            }
            if(!anyMatched){
                List<Object> emptyRow = new ArrayList<>();
                var _ignore = emptyRow.addAll(left.getRawRow(i));
                for(int k = 0; k < right.getColumnSize(); k++){
                    emptyRow.add(null);
                }
                rawResult.add(emptyRow);
            }
        }
        return rawResult;
    }
}

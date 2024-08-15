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

package keyhub.data.tbl.stream;

import keyhub.data.tbl.Tbl;
import keyhub.data.tbl.function.TblRowPredicate;
import keyhub.data.tbl.function.TblColumnSelector;
import keyhub.data.tbl.row.TblRow;
import keyhub.data.tbl.schema.TblColumn;
import keyhub.data.tbl.schema.TblSchema;

import java.util.stream.Stream;

public interface TblStream {
    static TblStream from(Stream<TblRow> rowStream) {
        return TblStreamImplement.from(rowStream);
    }

    TblSchema getSchema();

    Tbl toTbl();

    TblStream select(String... columns);
    TblStream select(TblColumn<?>... columns);
    TblStream select(TblColumnSelector... selector);
    TblStream where(TblRowPredicate filter);

}

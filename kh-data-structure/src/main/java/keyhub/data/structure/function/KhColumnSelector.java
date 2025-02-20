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

package keyhub.data.structure.function;

import keyhub.data.structure.column.KhColumn;
import keyhub.data.structure.schema.KhSchema;

import java.util.function.Function;

/**
 * Functional interface representing a selector that extracts a KhColumn from a KhSchema.
 */
@FunctionalInterface
public interface KhColumnSelector extends Function<KhSchema, KhColumn<?>> {

    /**
     * Applies this selector to the given schema.
     *
     * @param schema the input schema
     * @return the selected column
     */
    @Override
    KhColumn<?> apply(KhSchema schema);

    /**
     * Creates a column selector for the specified column name.
     *
     * @param columnName the name of the column
     * @return a column selector for the specified column
     * @throws IllegalArgumentException if the column is not found in the schema
     */
    static KhColumnSelector column(String columnName) {
        return schema -> {
            int index = schema.getColumnIndex(columnName);
            if (index == -1) {
                throw new IllegalArgumentException("KhColumn not found");
            }
            return schema.getColumnSchema(index);
        };
    }
}
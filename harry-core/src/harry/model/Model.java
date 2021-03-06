/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package harry.model;

import harry.core.Configuration;
import harry.ddl.SchemaSpec;
import harry.model.sut.SystemUnderTest;
import harry.runner.Query;
import harry.runner.QuerySelector;

public interface Model
{
    long NO_TIMESTAMP = Long.MIN_VALUE;

    void recordEvent(long lts, boolean quorumAchieved);

    void validatePartitionState(long verificationLts, Query query);

    Configuration.ModelConfiguration toConfig();

    interface ModelFactory
    {
        Model create(SchemaSpec schema,
                     OpSelectors.PdSelector pdSelector,
                     OpSelectors.DescriptorSelector descriptorSelector,
                     OpSelectors.MonotonicClock clock,
                     QuerySelector querySelector,
                     SystemUnderTest sut);
    }

    class ValidationException extends RuntimeException
    {
        public ValidationException()
        {
            super();
        }

        public ValidationException(String message)
        {
            super(message);
        }

        public ValidationException(String format, Object... objects)
        {
            super(String.format(format, objects));
        }

        public ValidationException(String message, Throwable cause)
        {
            super(message, cause);
        }
    }
}
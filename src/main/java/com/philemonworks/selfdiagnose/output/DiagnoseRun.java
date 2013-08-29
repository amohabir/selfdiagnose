/*
    Copyright 2008 Ernest Micklei @ PhilemonWorks.com

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
   
*/
package com.philemonworks.selfdiagnose.output;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.philemonworks.selfdiagnose.DiagnosticTaskResult;
import com.philemonworks.selfdiagnose.SelfDiagnose;

public class DiagnoseRun {
    public List<DiagnosticTaskResult> results = new ArrayList<DiagnosticTaskResult>();
    public long timeMs = System.currentTimeMillis();

    /**
     * Run has finished. Compute the execution time.
     */
    public void finished() {
        timeMs = System.currentTimeMillis() - timeMs;
    }

    public int howManyTasks() {
        return results.size();
    }

    public long totalTime() {
        return timeMs;
    }

    public int howManyNotPassed() {
        int noTasksFailed = 0;
        for (Iterator<DiagnosticTaskResult> iterator = results.iterator(); iterator.hasNext();) {
            DiagnosticTaskResult result = (DiagnosticTaskResult) iterator.next();
            boolean failed = result.isError() || result.isFailed();
            if (failed)
                noTasksFailed++;
        }
        return noTasksFailed;
    }

    public String getProductStamp() {
        return "SelfDiagnose " + SelfDiagnose.VERSION + " " + SelfDiagnose.COPYRIGHT;
    }

    public String getTotalsLine() {
        return "Checks: " + howManyTasks() + " , Failures: " + howManyNotPassed() + " , Time: " + totalTime() + " ms";
    }

    public boolean isOK() {
        return this.howManyNotPassed() == 0;
    }
}

(ns assassins.test-runner
  (:require
   [doo.runner :refer-macros [doo-tests]]
   [assassins.core-test]
   [assassins.common-test]))

(enable-console-print!)

(doo-tests 'assassins.core-test
           'assassins.common-test)

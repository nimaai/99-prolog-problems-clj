(ns ninety-nine-problems.lists.p22
  (:require [clojure.core.logic :as l]
            [clojure.core.logic.arithmetic :as la]
            [clojure.core.logic.fd :as lfd]
            [clojure.test :as t]))

(t/with-test
  (l/defne rangeo
    "i <= k, and l is the list containing all 
     consecutive integers from i to k."
    [i k l]
    ([n n [n]])
    ([n m [n . xs]] (l/fresh [n1]
                      (la/< n m)
                      (l/is n1 n inc)
                      (rangeo n1 m xs))))

  (t/is (= (l/run* [l] (rangeo 4 9 l))
           '([4,5,6,7,8,9]))))

(comment (t/run-tests))

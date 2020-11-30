(ns ninety-nine-problems.lists.p19
  (:require [clojure.core.logic :as l]
            [clojure.core.logic.arithmetic :as la]
            [clojure.core.logic.fd :as lfd]
            [clojure.test :as t]
            [ninety-nine-problems.lists.p04 :refer [lengtho]]
            [ninety-nine-problems.lists.p17 :refer [splito]]))

(t/with-test
  (defn rotateo 
    "The list l2 is obtained from the list l1 by 
     rotating the elements of l1 n places to the left."
    [l1 n l2]
    (letfn [(rotate-lefto [l1 n l2]
              (l/matche [l1 n l2]
                ([l 0 l])
                ([l1 n l2]
                 (la/> n 0)
                 (l/fresh [s1 s2]
                   (splito l1 n s1 s2)
                   (l/appendo s2 s1 l2)))))]
      (l/fresh [n1 nl1]
        (lengtho l1 nl1)
        (l/project [n nl1] (l/== n1 (mod n nl1)))
        (rotate-lefto l1 n1 l2))))

  (t/is (= (l/run* [x] (rotateo '[a,b,c,d,e,f,g,h], 3, x))
           '([d,e,f,g,h,a,b,c])))
  (t/is (= (l/run* [x] (rotateo '[a,b,c,d,e,f,g,h], -2, x))
           '([g,h,a,b,c,d,e,f]))))

(comment (t/run-tests))

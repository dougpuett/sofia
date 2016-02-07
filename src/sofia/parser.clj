(ns sofia.parser
  (:gen-class))

;; Imports
(use '[clojure.test])
(use '[clojure.string])

;; Functions
(defn process-one [in-string]
    (case 
        in-string nil {}
        (let [key-split (split in-string #" ")]
            (case
                (count key-split) 1 {(keyword (first key-split)) true}
                {(keyword (first key-split)) (vec (rest key-split))}
            )
        )
    )
)

(defn process-all-tags [in-array]
    (case 
        in-array [] {}
        (reduce merge {} (mapv process-one in-array))
    )
)

(defn parser [in-string] 
    (let [
        key-split (split in-string #" :" )
        tags (process-all-tags (rest key-split))
        ] 
       (merge 
            {
                :main (first key-split) 
                :main-literal in-string
            } 
            tags
        )
    )
)

;; Parser Tests
(is (= (process-one nil) {}))
(is (= (process-one "tag") {:tag true}))
(is (= (process-one "key value") {:key ["value"]}))
(is (= (process-one "key value1 value2") {:key ["value1" "value2"]}))
(is (= (process-all-tags ()) {}))
(is (= (process-all-tags ["a"]) {:a true}))
(is (= (process-all-tags ["a" "b"]) {:a true :b true}))
(is (= (process-all-tags ["a" "b" "c"]) {:a true :b true :c true}))
(is (= (process-all-tags ["a" "b" "c d"]) {:a true :b true :c ["d"]}))
(is (= (parser "a") {:main "a", :main-literal "a"}))
(is (= (parser "a b") {:main "a b", :main-literal "a b"}))
(is (= (parser "a b :tag") {:main "a b", :main-literal "a b :tag", :tag true}))
(is (= (parser "a b :tag1 :tag2") {:main "a b", :main-literal "a b :tag1 :tag2", :tag1 true :tag2 true}))
(is (= (parser "a b :tag1 :tag2 :tag3") {:main "a b", :main-literal "a b :tag1 :tag2 :tag3", :tag1 true :tag2 true :tag3 true}))
(is (= (parser "a b :tag1 :tag2 :tag3 :key value") {:main "a b", :main-literal "a b :tag1 :tag2 :tag3 :key value", :tag1 true :tag2 true :tag3 true :key ["value"]}))
(is (= (parser "a b :tag1 :tag2 :key value :tag3") {:main "a b", :main-literal "a b :tag1 :tag2 :key value :tag3", :tag1 true :tag2 true :tag3 true :key ["value"]}))
(is (= (parser "a b :key value1 :key value2") {:main "a b", :main-literal "a b :key value1 :key value2", :key ["value2"]}))
(ns quick-type.core
  (:require [active.clojure.record :as r]
            [active.clojure.sum-type :as s]))


(defn define-record-type [type-name field-names]
  (let [constructor (symbol (str "make-" type-name))
        predicate   (symbol (str type-name "?"))
        fields      (vec (mapcat (fn [f] (vector f (symbol (str type-name "-" f))))
                           field-names))]
    `(r/define-record-type
       ~type-name
       {:rtd-record? true
        :java-class? false}
       ~constructor
       ~predicate
       ~fields)))


(defn define-sum-type [type-name members]
  (let [names (mapv (fn [member]
                     (if (list? member)
                       (first member)
                       member))
                members)
        new-records (filter list? members)
        new-record-defs (map (fn [[x & xs]]
                               (define-record-type x xs)) new-records)
        predicate   (symbol (str type-name "?"))
        ]
    `(do
       ~@new-record-defs

       (s/define-sum-type ~type-name ~predicate ~names))))





(defmacro define-type [name & forms]
  (let [first-form (first forms)]
    (if (vector? first-form)
      (define-sum-type name first-form)
      (define-record-type name forms))))

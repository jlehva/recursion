(ns recursion)

(defn product [coll]
  (if (empty? coll)
    1
    (* (first coll)
       (product (rest coll)))))

(defn singleton? [coll]
  (and
    (empty? (rest coll))
    (not (empty? coll))))

(defn my-last [coll]
  (cond
    (empty? coll) nil
    (singleton? coll) (first coll)
    :else (my-last (rest coll))))

(defn max-element [a-seq]
  (cond
    (empty? a-seq) nil
    (singleton? a-seq) (first a-seq)
    :else (max (first a-seq) (max-element (rest a-seq)))))

(defn seq-max [seq-1 seq-2]
  (if (> (count seq-1) (count seq-2))
    seq-1
    seq-2))

(defn longest-sequence [a-seq]
  (cond
    (empty? a-seq) nil
    (nil? (second a-seq)) (first a-seq)
    :else (seq-max (first a-seq) (longest-sequence (rest a-seq)))))

(defn my-filter [pred? a-seq]
  (cond
    (empty? a-seq) a-seq
    :else (cond (pred? (first a-seq))
      (cons (first a-seq) (my-filter pred? (rest a-seq)))
      :else (my-filter pred? (rest a-seq)))))

(defn sequence-contains? [elem a-seq]
  (cond
    (empty? a-seq) false
    (= elem (first a-seq)) true
    :else (sequence-contains? elem (rest a-seq))))

(defn my-take-while [pred? a-seq]
  (cond
    (empty? a-seq)
      a-seq
    (pred? (first a-seq))
      (cons (first a-seq) (my-take-while pred? (rest a-seq)))
    :else
      (empty a-seq)))

(defn my-drop-while [pred? a-seq]
  (cond
    (empty? a-seq)
      a-seq
    (pred? (first a-seq))
      (my-drop-while pred? (rest a-seq))
    :else
      a-seq))

(defn seq= [a-seq b-seq]
  (cond
    (and (empty? a-seq) (empty? b-seq))
      true
    (or (empty? a-seq) (empty? b-seq))
      false
    (= (first a-seq) (first b-seq))
      (seq= (rest a-seq) (rest b-seq))
    :else
      false))

(defn my-map [f seq-1 seq-2]
  (cond
    (empty? seq-1) seq-1
    (empty? seq-2) seq-2
    :else
      (cons
        (f (first seq-1) (first seq-2))
        (my-map f (rest seq-1) (rest seq-2)))))

(defn power [n k]
  (if (zero? k)
    1
    (* n (power n (dec k)))))

(defn fib [n]
  (cond
    (= 0 n) n
    (= 1 n) n
    :else
      (+ (fib (dec n)) (fib (- n 2)))))

(defn my-repeat [how-many-times what-to-repeat]
  (if (< how-many-times 1)
    ()
    (cons what-to-repeat (my-repeat (dec how-many-times) what-to-repeat))))

(defn my-range [up-to]
  (if (= up-to 0)
    ()
    (let [n (dec up-to)]
      (cons n (my-range n)))))

(defn tails [a-seq]
  (if (empty? a-seq)
    (list ())
    (cons a-seq (tails (rest a-seq)))))

(defn inits [a-seq]
  (if (empty? a-seq)
    (list ())
    (reverse (map reverse (tails (reverse a-seq))))))

(defn rotate [a-seq times]
  (if (= times 0)
    ()
    (cons a-seq (rotate (concat (rest a-seq) (list (first a-seq))) (dec times)))))

(defn rotations [a-seq]
  (if (= (count a-seq) 0)
    (list ())
    (rotate a-seq (count a-seq))))

(defn my-frequencies-helper [freqs a-seq]
  (if (empty? a-seq)
    freqs
    (let [elem (first a-seq)]
     (if (find freqs elem)
        (my-frequencies-helper (update-in freqs [elem] inc)(rest a-seq))
        (my-frequencies-helper (assoc freqs elem 1) (rest a-seq))))))

(defn my-frequencies [a-seq]
  (my-frequencies-helper {} a-seq))

(defn un-frequencies [a-map]
  (if (empty? a-map)
    a-map
    (let [[key times] (first a-map)]
      (concat (repeat times key) (un-frequencies (rest a-map))))))

(defn my-take [n coll]
  (cond
    (= n 0)
      ()
    (empty? coll)
      ()
    :else
      (cons (first coll) (my-take (dec n) (rest coll)))))

(defn my-drop [n coll]
  (cond
    (= n 0)
      coll
    (empty? coll)
      coll
    :else
      (my-drop (dec n) (rest coll))))

(defn halve [a-seq]
  (let [n (int (/ (count a-seq) 2))]
    (vector (take n a-seq) (drop n a-seq))))

(defn seq-merge [a-seq b-seq]
  (cond
    (empty? a-seq)
      b-seq
    (empty? b-seq)
      a-seq
    :else
      (let [a (first a-seq)
            b (first b-seq)]
        (if (< a b)
          (cons a (seq-merge (rest a-seq) b-seq))
          (cons b (seq-merge a-seq (rest b-seq)))))))

(defn merge-sort [a-seq]
  (if (< (count a-seq) 2)
    a-seq
    (let [[a b] (halve a-seq)]
      (seq-merge (merge-sort a) (merge-sort b)))))

(defn monotonic? [a-seq]
  (or
    (empty? a-seq)
    (apply <= a-seq)
    (apply >= a-seq)))

(defn split-into-monotonics [a-seq]
  (if (empty? a-seq)
    ()
    (let [inits-seq (inits a-seq)
          monotonics (take-while monotonic? inits-seq)
          rest-aseq (drop (- (count monotonics) 1) a-seq)]
      (cons (last monotonics) (split-into-monotonics rest-aseq)))))

(defn permutations [a-set]
  [:-])

(defn powerset [a-set]
  [:-])


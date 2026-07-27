# Java 알고리즘/코딩테스트 문법 핵심 정리

Java로 알고리즘 및 코딩테스트 문제를 풀 때 실전에서 자주 사용하는 문법과 자료구조를 정리한 문서입니다.

---

## 목차

1. [입출력](#입출력)
   - [기본 입력](#기본-입력)
   - [한 줄에 여러 입력](#한-줄에-여러-입력)
   - [공백 없는 문자열](#공백-없는-문자열)
   - [출력](#출력)
     - [StringBuilder](#stringbuilder)
     - [StringBuilder vs StringBuffer](#stringbuilder-vs-stringbuffer)
2. [자료구조](#자료구조)
   - [Array](#array)
     - [Arrays.fill()](#arraysfill)
     - [Arrays.copyOf()](#arrayscopyof)
   - [Collection](#collection)
   - [List](#list)
   - [Set](#set)
     - [TreeSet](#treeset)
   - [Map](#map)
   - [Deque](#deque)
   - [PriorityQueue](#priorityqueue)
   - [Graph](#graph)
     - [인접 리스트](#인접-리스트)
     - [인접 행렬](#인접-행렬)
     - [간선 리스트](#간선-리스트)
   - [ArrayList vs LinkedList vs int[]](#arraylist-vs-linkedlist-vs-int)
   - [List.of() vs Arrays.asList()](#listof-vs-arraysaslist)
3. [정렬](#정렬)
   - [Arrays.sort()](#arrayssort)
   - [Collections.sort()](#collectionssort)
   - [Comparable vs Comparator](#comparable-vs-comparator)
4. [기타](#기타)
   - [length vs length() vs size()](#length-vs-length-vs-size)
   - [Math](#math)
   - [문자열 ↔ 숫자 변환](#문자열--숫자-변환)
   - [자주 쓰는 import](#자주-쓰는-import)

---

# 입출력

## 기본 입력

```java
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 한 줄씩 입력
        String str = br.readLine();
        int i = Integer.parseInt(br.readLine()); // int는 Integer.parseInt()로 변환
    }
}
```

## 한 줄에 여러 입력

```java
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()); // 토큰화하여 임시 저장
        String str = st.nextToken(); // 공백 기준으로 잘라서 반환
    }
}
```

## 공백 없는 문자열

아래와 같이 공백 없이 입력이 주어지는 경우에는 `StringTokenizer`를 사용할 수 없습니다.

```text
BBWB
BWBW
BBWB
WBBW
```

이때는 문자열로 받은 뒤 `charAt()`을 사용합니다.

```java
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];

        for (int i = 0; i < N; i++) {
            String line = br.readLine(); // 문자열 한 줄 입력

            for (int j = 0; j < N; j++) {
                board[i][j] = line.charAt(j); // 한 글자씩 저장
            }
        }
    }
}
```

## 출력

- `System.out.print()`: 출력 후 개행 없음
- `System.out.println()`: 출력 후 개행 있음

### StringBuilder

`System.out.print()`를 여러 번 호출하면 시간 초과가 발생할 수 있습니다.
이 경우 `StringBuilder`에 결과를 누적한 뒤 한 번에 출력합니다.

```java
class Main {
    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        int answer = 0;
        sb.append(answer).append('\n'); // 개행문자도 append 필요

        System.out.println(sb.toString()); // .toString()은 생략 가능
    }
}
```

### StringBuilder vs StringBuffer

| 구분 | StringBuilder | StringBuffer |
|------|---------------|--------------|
| thread-safe | ❌ | ✅ |
| 속도 | 상대적으로 빠름 | 동기화 비용으로 상대적으로 느림 |

알고리즘 문제 풀이는 멀티스레드를 고려할 필요가 없으므로, 더 빠른 **`StringBuilder`** 사용을 권장합니다.

---

# 자료구조

Java의 대표 자료구조로는 `List`, `Set`, `Map`이 있습니다.
이들은 `Collection`을 상속받으므로 `Collection`의 메서드를 공통으로 사용할 수 있습니다.

자주 사용하는 함수는 **볼드체**로 표시했습니다.

## Array

```java
class Main {
    public static void main(String[] args) throws Exception {
        int[] arr = new int[10];
    }
}
```

### Arrays.fill()

```java
class Main {
    public static void main(String[] args) throws Exception {
        // 1차원 배열 채우기
        int[] arr = new int[10];
        Arrays.fill(arr, -1); // arr = { -1, -1, ..., -1 }

        // 2차원 배열 채우기
        int[][] arr2 = new int[10][10];
        for (int i = 0; i < 10; i++) {
            Arrays.fill(arr2[i], -1);
        }
    }
}
```

### Arrays.copyOf()

```java
class Main {
    public static void main(String[] args) throws Exception {
        int[] arr = { 1, 2, 3 };
        int[] copy = Arrays.copyOf(arr, arr.length);
    }
}
```

## Collection

| 분류 | 함수 | 파라미터 | 설명 |
|------|------|----------|------|
| 추가 | **`add(E e)`** | 추가할 원소 | 원소 하나 추가 |
| 전체 추가 | `addAll(Collection<? extends E> c)` | 추가할 컬렉션 | 컬렉션 전체 추가 |
| 포함 여부 | **`contains(Object o)`** | 찾을 원소 | 해당 원소가 있는지 |
| 전체 포함 여부 | `containsAll(Collection<?> c)` | 비교할 컬렉션 | 모든 원소를 포함하는지 |
| 동일 여부 | **`equals(Object o)`** | 비교 대상 | 컬렉션 내용이 같은지 |
| 비어있는지 | **`isEmpty()`** | - | 원소가 하나도 없는지 |
| 크기 | **`size()`** | - | 원소 개수 |
| 전체 삭제 | **`clear()`** | - | 모든 원소 삭제 |
| 삭제 | **`remove(Object o)`** | 삭제할 원소 | 해당 원소 삭제 |
| 전체 삭제 | `removeAll(Collection<?> c)` | 삭제할 컬렉션 | 컬렉션에 포함된 원소 전부 삭제 |
| 배열 변환 | **`toArray()`** | - | 배열로 변환 |

## List

List의 제네릭 타입은 반드시 **참조 타입(Reference Type)** 이어야 합니다.
`int`, `long`, `double`, `char` 같은 primitive type은 사용할 수 없습니다.

```java
class Main {
    public static void main(String[] args) throws Exception {
        List<Integer> intList = new ArrayList<>();
        List<Long> longList = new ArrayList<>();
        List<Double> doubleList = new ArrayList<>();
        List<Character> charList = new ArrayList<>();
        List<Boolean> booleanList = new ArrayList<>();
        List<String> stringList = new ArrayList<>();

        List<int> list = new ArrayList<>();    // ❌ 컴파일 에러
        List<long> list = new ArrayList<>();   // ❌
        List<double> list = new ArrayList<>(); // ❌

        list.add(1);
        list.get(0);
        list.set(0, 10);
        list.remove(0);
        list.size();
        list.contains(10);
        Collections.sort(list);
    }
}
```

| 분류 | 함수 | 시간복잡도 | 파라미터 | 설명 |
|------|------|-----------|----------|------|
| 추가 | **`add(E e)`** | 평균 `O(1)` | 추가할 원소 | 맨 뒤에 추가 |
| 위치 추가 | `add(int index, E e)` | `O(N)` | 인덱스, 원소 | 원하는 위치에 추가 |
| 전체 추가 | **`addAll(Collection<? extends E> c)`** | `O(M)` | 추가할 컬렉션 | 맨 뒤에 컬렉션 추가 |
| 위치 전체 추가 | `addAll(int index, Collection<? extends E> c)` | `O(N + M)` | 인덱스, 컬렉션 | 원하는 위치에 컬렉션 추가 |
| 조회 | **`get(int index)`** | `O(1)` | 인덱스 | 해당 위치 원소 반환 |
| 첫 번째 위치 | `indexOf(Object o)` | `O(N)` | 찾을 원소 | 처음 등장하는 인덱스 반환 |
| 마지막 위치 | `lastIndexOf(Object o)` | `O(N)` | 찾을 원소 | 마지막 등장하는 인덱스 반환 |
| 삭제(인덱스) | **`remove(int index)`** | `O(N)` | 인덱스 | 해당 위치 원소 삭제 후 반환 |
| 삭제(원소) | `remove(Object o)` | `O(N)` | 삭제할 원소 | 해당 원소 삭제 |
| 수정 | `set(int index, E e)` | `O(1)` | 인덱스, 원소 | 해당 위치 원소 변경 |

## Set

```java
class Main {
    public static void main(String[] args) throws Exception {
        Set<Integer> set = new HashSet<>();

        set.add(1);
        set.remove(1);
        set.contains(1);
        set.size();
    }
}
```

`HashSet`은 순서를 보장하지 않으므로 인덱스로 접근할 수 없습니다.
특정 값을 꺼내려면 순회하거나 배열/리스트로 변환해야 합니다.

```java
for (int num : set) {
    System.out.println(num);
}
```

| 분류 | 함수 | 시간복잡도 | 파라미터 | 설명 |
|------|------|-----------|----------|------|
| 추가 | **`add(E e)`** | 평균 `O(1)` | 추가할 원소 | 원소 추가 (중복이면 추가되지 않음) |
| 전체 추가 | `addAll(Collection<? extends E> c)` | `O(M)` | 추가할 컬렉션 | 컬렉션 전체 추가 |
| 포함 여부 | **`contains(Object o)`** | 평균 `O(1)` | 찾을 원소 | 해당 원소가 있는지 |
| 전체 포함 여부 | `containsAll(Collection<?> c)` | 평균 `O(M)` | 비교할 컬렉션 | 모든 원소를 포함하는지 |
| 삭제 | **`remove(Object o)`** | 평균 `O(1)` | 삭제할 원소 | 해당 원소 삭제 |
| 전체 삭제 | `removeAll(Collection<?> c)` | 평균 `O(M)` | 삭제할 컬렉션 | 컬렉션에 포함된 원소 전부 삭제 |
| 전체 삭제 | **`clear()`** | `O(N)` | - | 모든 원소 삭제 |
| 비어있는지 | **`isEmpty()`** | `O(1)` | - | 원소가 하나도 없는지 |
| 크기 | **`size()`** | `O(1)` | - | 원소 개수 |
| 배열 변환 | **`toArray()`** | `O(N)` | - | 배열로 변환 |

### TreeSet

원소를 자동으로 정렬하여 저장하는 Set입니다.
`HashSet`은 순서를 보장하지 않지만, `TreeSet`은 항상 정렬된 순서를 유지합니다.

```java
class Main {
    public static void main(String[] args) throws Exception {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(3);
        set.add(1);
        set.add(2);
        System.out.println(set); // [1, 2, 3]

        set.first();     // 최소값
        set.last();      // 최대값
        set.ceiling(4);  // 4 이상인 최소 원소
        set.floor(4);    // 4 이하인 최대 원소
        set.higher(4);   // 4 초과인 최소 원소
        set.lower(4);    // 4 미만인 최대 원소
    }
}
```

| 함수 | 설명 | 시간복잡도 |
|------|------|-----------|
| **`add(E e)`** | 원소 추가 | `O(log N)` |
| **`remove(Object o)`** | 원소 삭제 | `O(log N)` |
| **`contains(Object o)`** | 포함 여부 확인 | `O(log N)` |
| **`first()`** | 최소값 반환 | `O(log N)` |
| **`last()`** | 최대값 반환 | `O(log N)` |
| `ceiling(E e)` | `e` 이상인 최소 원소 반환 | `O(log N)` |
| `floor(E e)` | `e` 이하인 최대 원소 반환 | `O(log N)` |
| `higher(E e)` | `e` 초과인 최소 원소 반환 | `O(log N)` |
| `lower(E e)` | `e` 미만인 최대 원소 반환 | `O(log N)` |
| **`pollFirst()`** | 최소값 삭제 후 반환 | `O(log N)` |
| **`pollLast()`** | 최대값 삭제 후 반환 | `O(log N)` |
| **`isEmpty()`** | 원소가 비어있는지 확인 | `O(1)` |
| **`size()`** | 원소 개수 | `O(1)` |
| **`clear()`** | 모든 원소 삭제 | `O(N)` |

## Map

```java
class Main {
    public static void main(String[] args) throws Exception {
        Map<Integer, String> map = new HashMap<>();

        map.put(1, "A");
        map.get(1);
        map.getOrDefault(0, "B");
        map.remove(1);
        map.containsKey(1);
        map.containsValue("B");

        // key 순회
        for (Integer key : map.keySet()) {
        }
        // key-value 조합으로 순회
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
        }
    }
}
```

키가 없을 때 값을 생성해야 하는 경우 `computeIfAbsent()`를 사용하면 간결하게 작성할 수 있습니다.

```java
class Main {
    public static void main(String[] args) throws Exception {
        Map<K, List<V>> map = new HashMap<>();

        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<>());
        }
        map.get(key).add(value);

        // 위 코드를 한 줄로 작성
        map.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
    }
}
```

| 분류 | 함수 | 시간복잡도 | 파라미터 | 설명 |
|------|------|-----------|----------|------|
| 추가/수정 | **`put(K key, V value)`** | 평균 `O(1)` | 키, 값 | 키에 값 저장 (기존 키면 덮어쓰기) |
| 전체 추가 | `putAll(Map<? extends K, ? extends V> m)` | 평균 `O(M)` | 추가할 Map | Map 전체 추가 |
| 조회 | **`get(Object key)`** | 평균 `O(1)` | 키 | 해당 키의 값 반환 |
| 기본값 조회 | **`getOrDefault(Object key, V defaultValue)`** | 평균 `O(1)` | 키, 기본값 | 키가 없으면 기본값 반환 |
| 포함 여부(Key) | **`containsKey(Object key)`** | 평균 `O(1)` | 키 | 해당 키가 있는지 |
| 포함 여부(Value) | `containsValue(Object value)` | `O(N)` | 값 | 해당 값이 있는지 |
| 삭제 | **`remove(Object key)`** | 평균 `O(1)` | 키 | 해당 키 삭제 |
| 삭제(Key, Value) | `remove(Object key, Object value)` | 평균 `O(1)` | 키, 값 | 키와 값이 모두 일치하면 삭제 |
| 전체 삭제 | **`clear()`** | `O(N)` | - | 모든 데이터 삭제 |
| 비어있는지 | **`isEmpty()`** | `O(1)` | - | 데이터가 없는지 |
| 크기 | **`size()`** | `O(1)` | - | Key-Value 개수 |
| Key 조회 | `keySet()` | `O(1)` | - | 모든 Key를 Set(View)으로 반환 |
| Value 조회 | `values()` | `O(1)` | - | 모든 Value를 Collection(View)으로 반환 |
| Entry 조회 | **`entrySet()`** | `O(1)` | - | 모든 Entry(Key-Value)를 Set(View)으로 반환 |
| 없으면 생성 | **`computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction)`** | 평균 `O(1)` | 키, 생성 함수 | 키가 없으면 값을 생성 후 반환 |

## Deque

양쪽에서 삽입과 삭제가 가능한 자료구조입니다.
`Queue`와 `Stack`을 모두 구현할 수 있으므로 실전에서 가장 많이 사용합니다.

```java
class Main {
    public static void main(String[] args) throws Exception {
        Deque<Integer> dq = new ArrayDeque<>();

        dq.offer(1); // 큐처럼 사용
        dq.poll();   // 큐처럼 사용
        dq.push(2);  // 스택처럼 사용
        dq.pop();    // 스택처럼 사용
        dq.peek();
        dq.addFirst(1);
        dq.addLast(1);
        dq.pollFirst();
        dq.pollLast();
        dq.peekFirst();
        dq.peekLast();
    }
}
```

| 분류 | 함수 | 시간복잡도 | 파라미터 | 설명 |
|------|------|-----------|----------|------|
| 앞 추가 | **`addFirst(E e)`** | `O(1)` | 추가할 원소 | 맨 앞에 원소 추가, 실패 시 예외 |
| 뒤 추가 | **`addLast(E e)`** | `O(1)` | 추가할 원소 | 맨 뒤에 원소 추가, 실패 시 예외 |
| 앞 추가 | `offerFirst(E e)` | `O(1)` | 추가할 원소 | 맨 앞에 원소 추가, 실패 시 `false` |
| 뒤 추가 | **`offerLast(E e)`** | `O(1)` | 추가할 원소 | 맨 뒤에 원소 추가, 실패 시 `false` |
| 앞 조회 | `getFirst()` | `O(1)` | - | 맨 앞 원소 반환, 비어 있으면 예외 |
| 뒤 조회 | `getLast()` | `O(1)` | - | 맨 뒤 원소 반환, 비어 있으면 예외 |
| 앞 조회 | **`peekFirst()`** | `O(1)` | - | 맨 앞 원소 반환, 비어 있으면 `null` |
| 뒤 조회 | **`peekLast()`** | `O(1)` | - | 맨 뒤 원소 반환, 비어 있으면 `null` |
| 앞 삭제 | `removeFirst()` | `O(1)` | - | 맨 앞 원소 삭제 후 반환, 비어 있으면 예외 |
| 뒤 삭제 | `removeLast()` | `O(1)` | - | 맨 뒤 원소 삭제 후 반환, 비어 있으면 예외 |
| 앞 삭제 | **`pollFirst()`** | `O(1)` | - | 맨 앞 원소 삭제 후 반환, 비어 있으면 `null` |
| 뒤 삭제 | **`pollLast()`** | `O(1)` | - | 맨 뒤 원소 삭제 후 반환, 비어 있으면 `null` |
| 원소 삭제 | `removeFirstOccurrence(Object o)` | `O(N)` | 삭제할 원소 | 앞에서부터 처음 일치하는 원소 삭제 |
| 원소 삭제 | `removeLastOccurrence(Object o)` | `O(N)` | 삭제할 원소 | 뒤에서부터 처음 일치하는 원소 삭제 |
| 포함 여부 | `contains(Object o)` | `O(N)` | 찾을 원소 | 해당 원소가 있는지 확인 |
| 전체 삭제 | `clear()` | `O(N)` | - | 모든 원소 삭제 |
| 비어 있는지 | **`isEmpty()`** | `O(1)` | - | 원소가 하나도 없는지 |
| 크기 | **`size()`** | `O(1)` | - | 원소 개수 |

## PriorityQueue

우선순위에 따라 자동으로 정렬되는 Queue입니다. 기본은 오름차순입니다.

```java
class Main {
    public static void main(String[] args) throws Exception {
        // 기본(오름차순)
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // 내림차순
        PriorityQueue<Integer> pq1 = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> pq2 = new PriorityQueue<>((o1, o2) -> o2 - o1); // 오버플로우 위험 있음
        PriorityQueue<Integer> pq3 = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1)); // 권장
    }
}
```

| 분류 | 함수 | 시간복잡도 | 파라미터 | 설명 |
|------|------|-----------|----------|------|
| 추가 | **`offer(E e)`** | `O(log N)` | 추가할 원소 | 원소를 우선순위에 맞게 추가, 실패 시 `false` |
| 추가 | `add(E e)` | `O(log N)` | 추가할 원소 | 원소를 우선순위에 맞게 추가, 실패 시 예외 |
| 최우선 조회 | **`peek()`** | `O(1)` | - | 우선순위가 가장 높은 원소 반환, 비어 있으면 `null` |
| 최우선 조회 | `element()` | `O(1)` | - | 우선순위가 가장 높은 원소 반환, 비어 있으면 예외 |
| 최우선 삭제 | **`poll()`** | `O(log N)` | - | 우선순위가 가장 높은 원소 삭제 후 반환, 비어 있으면 `null` |
| 최우선 삭제 | `remove()` | `O(log N)` | - | 우선순위가 가장 높은 원소 삭제 후 반환, 비어 있으면 예외 |
| 특정 원소 삭제 | `remove(Object o)` | `O(N)` | 삭제할 원소 | 해당 원소를 찾아 삭제 |
| 포함 여부 | `contains(Object o)` | `O(N)` | 찾을 원소 | 해당 원소가 있는지 확인 |
| 전체 추가 | `addAll(Collection<? extends E> c)` | `O(M log(N + M))` | 추가할 컬렉션 | 컬렉션의 원소를 모두 추가 |
| 전체 삭제 | `clear()` | `O(N)` | - | 모든 원소 삭제 |
| 비어 있는지 | **`isEmpty()`** | `O(1)` | - | 원소가 하나도 없는지 |
| 크기 | **`size()`** | `O(1)` | - | 원소 개수 |
| 배열 변환 | `toArray()` | `O(N)` | - | 배열로 변환 |

## Graph

Java에는 그래프 자료구조가 기본으로 제공되지 않으므로 직접 구현해야 합니다.
대표적인 구현 방법은 **인접 리스트**, **인접 행렬**, **간선 리스트**입니다.

### 인접 리스트

각 정점마다 연결된 정점들의 목록을 저장하는 방식입니다. 그래프 구현 시 가장 일반적으로 사용됩니다.

```java
class Main {
    public static void main(String[] args) throws Exception {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        graph.get(1).add(2);
        graph.get(1).add(3);
        graph.get(2).add(4);

        // 가중치가 있으면 int[] { 다음 정점, 가중치 } 로 저장
        List<List<int[]>> weightedGraph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            weightedGraph.add(new ArrayList<>());
        }
        weightedGraph.get(1).add(new int[] { 2, 5 });
        weightedGraph.get(1).add(new int[] { 3, 2 });

        // 순회
        for (int[] edge : weightedGraph.get(now)) {
            int next = edge[0];
            int cost = edge[1];
        }
    }
}
```

- 공간복잡도: **`O(V + E)`**
- 특정 정점의 인접 정점 순회: **`O(degree(V))`**
- 두 정점의 연결 여부 확인: **`O(degree(V))`**
- 간선 추가: 평균 **`O(1)`**

### 인접 행렬

`graph[i][j]`에 정점 i와 j의 연결 여부 또는 가중치를 저장하는 방식입니다.
정점 수가 작거나 두 정점의 연결 여부를 자주 확인해야 할 때 사용합니다.

```java
class Main {
    public static void main(String[] args) throws Exception {
        int[][] graph = new int[N + 1][N + 1];
        graph[1][2] = 1;
        graph[1][3] = 1;

        // 가중치 그래프는 가중치 저장
        graph[1][2] = 5;
        graph[2][1] = 3;
    }
}
```

- 공간복잡도: **`O(V²)`**
- 특정 정점의 인접 정점 순회: **`O(V)`**
- 두 정점의 연결 여부 확인: **`O(1)`**
- 간선 추가: 평균 **`O(1)`**

### 간선 리스트

그래프의 모든 간선을 하나의 리스트에 저장하는 방식입니다.

```java
class Main {
    public static void main(String[] args) throws Exception {
        List<int[]> edges = new ArrayList<>();
        // { 출발 정점, 도착 정점, 가중치 }
        edges.add(new int[]{ 1, 2, 5 });
        edges.add(new int[]{ 1, 3, 10 });

        // 순회
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int cost = edge[2];
        }
    }
}
```

- 공간복잡도: **`O(E)`**
- 전체 간선 순회: **`O(E)`**
- 특정 정점의 인접 정점 순회: **`O(E)`**
- 두 정점의 연결 여부 확인: **`O(E)`**
- 간선 추가: 평균 **`O(1)`**

## ArrayList vs LinkedList vs int[]

| 구분 | `ArrayList<Integer>` | `LinkedList<Integer>` | `int[]` |
|------|----------------------|-----------------------|---------|
| 구조 | 동적 배열 | 연결 리스트 | 고정 배열 |
| 크기 변경 | ✅ | ✅ | ❌ |
| 인덱스 조회 | `O(1)` | `O(N)` | `O(1)` |
| 마지막 추가 | 평균 `O(1)` | `O(1)` | ❌ |
| 중간 삽입·삭제 | `O(N)` | 탐색 포함 `O(N)` | 직접 이동 |
| primitive 저장 | ❌ | ❌ | ✅ |
| 코테 사용 빈도 | ⭐⭐⭐⭐⭐ | ⭐ | ⭐⭐⭐⭐⭐ |

### 핵심 특징

**`ArrayList`**
- 인덱스 조회가 빠름 (`O(1)`)
- 중간 삽입·삭제는 느림 (`O(N)`)
- 코딩테스트에서 가장 많이 사용하는 List

**`LinkedList`**
- 삽입·삭제 자체는 빠르지만 위치 탐색이 `O(N)`
- 코딩테스트에서는 거의 사용하지 않음
- Queue/Deque는 **`ArrayDeque`** 사용 권장

**`int[]`**
- 크기가 고정되어 있음
- primitive를 직접 저장하여 메모리 효율과 성능이 가장 좋음
- 크기를 미리 알 수 있다면 가장 권장

## List.of() vs Arrays.asList()

여러 값을 이용해 List를 간단하게 생성할 수 있습니다.

```java
class Main {
    public static void main(String[] args) throws Exception {
        List<Integer> list1 = List.of(1, 2, 3);
        List<Integer> list2 = Arrays.asList(1, 2, 3);
    }
}
```

두 방식 모두 크기가 고정되어 있어 `add()`, `remove()`는 사용할 수 없습니다.

| 구분 | `List.of()` | `Arrays.asList()` |
|------|-------------|-------------------|
| `add()`, `remove()` | ❌ | ❌ |
| `set()` | ❌ | ✅ |
| `null` 저장 | ❌ | ✅ |
| 원본 배열과 연결 | ❌ | ✅ |

### 수정 가능한 리스트 만들기

```java
List<Integer> list1 = new ArrayList<>(List.of(1, 2, 3));
List<Integer> list2 = new ArrayList<>(Arrays.asList(1, 2, 3));
```

---

# 정렬

정렬의 기본은 **오름차순**입니다.

## Arrays.sort()

배열을 정렬할 때 사용합니다.
**primitive 배열**은 Comparator를 사용할 수 없고, **객체 배열(`Integer[]` 등)** 은 Comparator를 사용할 수 있습니다.

```java
class Main {
    public static void main(String[] args) throws Exception {
        // 오름차순
        int[] arr = new int[10];
        Arrays.sort(arr);

        // 내림차순 (Wrapper 클래스여야 Comparator 사용 가능)
        Integer[] arr2 = { 1, 2, 3 };
        Arrays.sort(arr2, Comparator.reverseOrder());
    }
}
```

## Collections.sort()

`Collection`을 정렬할 때 사용합니다.

```java
class Main {
    public static void main(String[] args) throws Exception {
        List<Integer> list = new ArrayList<>();
        Collections.sort(list);
    }
}
```

## Comparable vs Comparator

### Comparable

객체 내부에서 기본 정렬 기준을 정의합니다.

```java
class Node implements Comparable<Node> {
    int cost;

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.cost, o.cost);
    }
}
```

### Comparator

정렬할 때마다 기준을 바꾸고 싶을 때 사용합니다.

```java
Collections.sort(list, (o1, o2) -> {
    if (o1.cost != o2.cost) return Integer.compare(o1.cost, o2.cost);
    return Integer.compare(o1.idx, o2.idx);
});
```

---

# 기타

## length vs length() vs size()

- `length`: **배열**의 길이
- `length()`: **String**의 길이
- `size()`: **Collection**의 길이

```java
class Main {
    public static void main(String[] args) throws Exception {
        int[] arr = new int[10];
        System.out.println(arr.length); // 10

        String str = "안녕하세요";
        System.out.println(str.length()); // 5

        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));
        System.out.println(list.size()); // 3

        // Set, Map도 동일
        set.size();
        map.size();
    }
}
```

## Math

```java
Math.max(a, b);  // 최대값
Math.min(a, b);  // 최소값
Math.abs(x);     // 절대값
Math.sqrt(x);    // 제곱근
Math.pow(a, b);  // 거듭제곱
Math.ceil(x);    // 올림
Math.floor(x);   // 내림
Math.round(x);   // 반올림
```

## 문자열 ↔ 숫자 변환

```java
int n = Integer.parseInt(str);
long l = Long.parseLong(str);
String s = String.valueOf(n);
```

## 자주 쓰는 import

아래 두 가지 import로 대부분의 문제를 해결할 수 있습니다.

```java
import java.io.*;
import java.util.*;
```

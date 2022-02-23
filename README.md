# shlee-elasticsearch-boilerplate

* 소개
  + Java maven 프로젝트 환경을 기반으로하여, Elastic Search에 저장된 데이터에 접근하고 가져오는 형태이다.


  
  
## Exception
* ElasticsearchStatusException[Elasticsearch exception [type=illegal_argument_exception, reason=request [/recordlist1/_search] contains unrecognized parameter: [ccs_minimize_roundtrips]]]
  + elasticsearch의 버전이슈로 인한 에러상황

---
그 외에도 컴파일 도중 다양한 에러가 발생하나, 대부분 버전이슈인 경우가 많았음...

pom.xml 에서 elasticsearch와 관련된 dependency의 버전들을 신경쓰자!

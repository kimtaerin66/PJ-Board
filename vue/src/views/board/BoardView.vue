<template>
  <section class="contents-wrap">
    <LocationCom />
    <div class="contents">
      <h2 class="title">{{ $route.name }}</h2>
      <div class="list-wrap">
        <div class="list-h">
          <div class="list-h-l">
            <select class="page-select" v-model="selected">
              <option
                v-for="num in pageNum"
                :key="num.value"
                :value="num.label"
              >
                {{ num.label }}
              </option>
            </select>
            <span class="total_info"
              >총 <strong>{{ totalCount }}</strong
              >개의 검색 결과가 있습니다.
            </span>
          </div>
          <div class="list-h-r">
            <div class="setDate">
              <div class="fromDate">시작날짜</div>
              <div class="toDate">종료날짜</div>
            </div>
            <div class="search">검색</div>
          </div>
        </div>
        <div class="list-m">
          <table class="table-list">
            <colgroup>
              <col width="8%" />
              <col width="12%" />
              <col width="15%" />
              <col width="18%" />
              <col width="18%" />
              <col width="25%" />
            </colgroup>
            <thead>
              <tr>
                <th>#</th>
                <th>아이디</th>
                <th>이름</th>
                <th>이메일</th>
                <th>휴대폰 번호</th>
                <th>가입날짜</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(user, index) in userList" :key="user.id">
                <td>{{ index + 1 }}</td>
                <td>{{ user.userId }}</td>
                <td>{{ user.userName }}</td>
                <td>{{ user.userEmail }}</td>
                <td>{{ user.userPhone }}</td>
                <td>{{ user.userRegDate }}</td>
              </tr>
            </tbody>
          </table>
        </div>
        <div class="list-b"></div>
      </div>
    </div>
  </section>
</template>

<script>
import LocationCom from '@/common/LocationCom';
import axios from 'axios';

export default {
  components: { LocationCom },
  data() {
    return {
      totalCount: 0,
      selected: '10개',
      pageNum: [
        { label: '10개', value: 10 },
        { label: '20개', value: 20 },
        { label: '30개', value: 30 },
        { label: '40개', value: 40 },
        { label: '50개', value: 50 },
      ],
      userList: [],
    };
  },
  methods: {
    async search() {
      const { data } = await axios.get('/api/user/list');
      this.userList = data.result;
      console.log(this.userList);
    },
  },
  created() {
    this.search();
  },
};
</script>

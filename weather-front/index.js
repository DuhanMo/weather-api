var app = new Vue({
  el: "#app",
  data: {
    locations: [
      { text: "서울", xy: ["60", "27"] },
      { text: "부산", xy: ["98", "76"] },
      { text: "대구", xy: ["89", "90"] },
      { text: "인천", xy: ["55", "124"] },
      { text: "광주", xy: ["58", "74"] },
      { text: "대전", xy: ["67", "100"] },
      { text: "울산", xy: ["102", "84"] },
      { text: "세종", xy: ["66", "103"] },
      { text: "경기", xy: ["60", "120"] },
      { text: "강원", xy: ["73", "134"] },
      { text: "충북", xy: ["69", "107"] },
      { text: "충남", xy: ["68", "100"] },
      { text: "전북", xy: ["63", "89"] },
      { text: "전남", xy: ["51", "67"] },
      { text: "경북", xy: ["89", "91"] },
      { text: "경남", xy: ["91", "77"] },
      { text: "제주", xy: ["52", "38"] },
    ],
  },
  mounted() {
    axios
      .get("http://localhost:8080")
      .then((response) => (this.info = response));
  },
  methods: {
    fetchData: function (index) {
      console.log(this.locations[index].text + "의 날씨는 --------");
      axios
        .get("http://localhost:8080/api/weather", {
          params: {
            xy: this.locations[index].xy,
          },
          paramsSerializer: (params) => {
            return jQuery.param(params);
          },
        })
        .then(function (res) {
          console.log(res.data);
        })
        .catch(function (error) {
          console.log(error);
        });
    },
  },
});

<template>
  <div id="totals" style="width:80%;margin-left:10%;margin-right:10%;margin-top:100px;height:500px"></div>
</template>

<script>
import * as echarts from 'echarts'
export default {
    data(){
        return{
            total:[],
        }
    },
    methods:{
        getTotal(){
            let myChart = echarts.init(document.getElementById('totals'));
        let option = {
            title: {
            text: '各年份费用统计'
            },
            tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
            },
            legend: {},
            grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
            },
            xAxis: {
            type: 'value',
            boundaryGap: [0, 0.01]
            },
            yAxis: {
            type: 'category',
            data: ["2019", '2020', '2021', '2022']
            },
            series: [
            {
                type: 'bar',
                data: [this.total[0], this.total[1], this.total[2],this.total[3]]
            }
            ]
        };
        myChart.setOption(option)
        window.addEventListener('resize',function() {myChart.resize()});
        }
    },
    mounted() {
        this.getTotal()
    },
    created:function(){
        var _this = this
        _this.$axios({
            url:"order/getTotal",
            method:"post"
        }).then(function(resp){
            if(resp.data!==''){
                _this.total=resp.data
            }
        }).catch(function(err){
            _this.$message.error(err.data)
        })
    },
    watch:{
        total(){
            this.getTotal()
        }
    }

}
</script>

<template>
    <div class="container">
        <img src="@/assets/imgs/bg_apply_project_one_left.png"/>
        <img class ="middle" src="@/assets/imgs/bg_apply_project_one_middle.png"/>
        <img src="@/assets/imgs/bg_apply_project_one_right.png"/>
        <div class="text-div">
            <span class="text">{{tetx}}</span>
        </div>
    </div>
</template>

<script>
export default {
    props: {
        tetx: ''
    }
}
</script>

<style scoped>
.container{
    /* width: 100%; */
    height: 68px;
    display: flex;
    flex-direction: row;
    position: relative;
}
.middle {
    flex-grow: 1;
    width: auto;

    height: auto;

    max-width: 100%;

    max-height: 100%;
}
.text-div{
    width: 100%;
    height: 100%;
    position:absolute;
    display: table;
}
.text {
    display: table-cell;
    text-align: center;
    vertical-align: middle;
    margin: auto;
    font-size: 18px;
    font-family: Barlow-Medium, Barlow;
    font-weight: 500;
    color: #242B35;
}

</style>
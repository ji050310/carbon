<template>
  <div class="root">
    <div>
      <a class="nav-title">碳行业资讯</a>
    </div>
    <div class="card-container">
      <div class="left-part-news-container">
        <div class="tab-container">
          <el-tabs v-model="activeName" :before-leave="moreState">
            <el-tab-pane class="lable1" label="动态" name="first" :key="'first'">
              <news-list />
            </el-tab-pane>
            <el-tab-pane class="lable2" label="知识库" name="second" :key="'second'">
              <knowleage-list />
            </el-tab-pane>
            <el-tab-pane name="more">
              <div slot="label" class="tab-more">
                <div>
                  <a class="more-text" @click="openMoreNews" style="position: relative;left: 70px;">查看更多 ></a>
                </div>
              </div>
            </el-tab-pane>
          </el-tabs>
        </div>
      </div>
      <div class="right-part-news-container">
        <div class="right-part-news-top-container">
          <div class="notice-contianer">
            <a class="notice-text">公告</a>
            <a class="more-text" @click="openMoreNotic">查看更多 ></a>
          </div>
          <div class="my-divier" />
          <notice-list />
        </div>

        <div class="right-part-news-bottom-container">
          <div class="notice-contianer">
            <a class="notice-text">常见问题</a>
            <a class="more-text" @click="openMoreQuestion">查看更多 ></a>
          </div>
          <div class="my-divier" />
          <question-list />
        </div>

      </div>
    </div>
  </div>
</template>
<script>
import knowleageList from './knowleageList.vue';
import newsList from './newsList.vue';
import questionList from './questionList.vue';
import noticeList from './noticeList.vue';
import { openUrlInNewWindow } from '@/libs/OpenHelper'
export default {
  components: {
    newsList,
    knowleageList,
    noticeList,
    questionList,
  },
  name: 'news',
  data() {
    return {
      //默认第一个选项卡
      activeName: "first",
      moreNewsUrl: 'https://carbonmsger.feishu.cn/drive/folder/fldcnAvHTRCizVaPTyzYQG6Glge',
      moreKnowUrl: 'https://carbonmsger.feishu.cn/drive/folder/fldcnSjhUFqVKWJAgkGijgdF7Dh',
      moreQuestUrl: 'https://carbonmsger.feishu.cn/drive/folder/fldcn9rj1BekKj2OvJvzTF7VMRd?from=space_persnoal_filelist',
      moreNoticeUrl: 'https://carbonmsger.feishu.cn/drive/folder/fldcnJZLoVUcn9tDZnacv4jqySg',
      curTab: ''
    }
  },
  mounted() {

  },
  methods: {
    openMoreQuestion() {
      openUrlInNewWindow(this.moreQuestUrl);
    },
    openMoreNotic() {
      openUrlInNewWindow(this.moreNoticeUrl)
    },
    openMoreNews() {
      if (this.activeName == 'first') {
        openUrlInNewWindow(this.moreNewsUrl)
      } else if (this.activeName == 'second') {
        openUrlInNewWindow(this.moreKnowUrl)
      }
    },
    moreState(tab, event) {
      if (tab == 'more') {
        this.curTab = ''
        return false;
      } else {
        this.curTab = tab
      }
    }
  },
  created() {

  }
}
</script>
<style lang="scss" scoped>
.root {
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 489px;
  margin-top: 20px;
  margin-left: 20px;
  margin-bottom: 10px;
}

.card-container {
  margin-top: 6px;
  margin-right: 26px;
  // width: 100%;
  height: 100%;
  display: grid;
  // flex-direction: row;
  grid: auto / 55% 45%;
}

.left-part-news-container {
  // width: 720px;
  height: 100%;
  margin-right: 14px;
  background: #FFFFFF;
  box-shadow: 0px 2px 8px 0px #EAF0F3;
  border-radius: 8px;
}

.right-part-news-container {
  margin-right: 16px;
  // flex-grow: 1;
  height: 100%;
}

.right-part-news-top-container {
  // width: 414px;
  padding: 10px;
  display: flex;
  flex-direction: column;
  height: 236px;
  background: #FFFFFF;
  box-shadow: 0px 2px 8px 0px #EAF0F3;
  border-radius: 8px;
}

.right-part-news-bottom-container {
  margin-top: 10px;
  // width: 414px;
  padding: 10px;
  display: flex;
  flex-direction: column;
  height: 236px;
  background: #FFFFFF;
  box-shadow: 0px 2px 8px 0px #EAF0F3;
  border-radius: 8px;
}

.tab-container {
  padding: 10px;
  position: relative;
}

.notice-contianer {
  display: flex;
  flex-direction: row;
  padding-top: 10px;
  padding-left: 10px;
  padding-bottom: 10px;
}

.notice-text {


  font-weight: 500;
  color: #242B35;
}

.more-text {
  flex-grow: 1;
  text-align: right;


  font-weight: 400;
  color: #5E6C84;
}

.my-divier {
  height: 1px;
  border: 1px solid rgba(238, 240, 243, 0.95);
}

.nav-title {
  margin-top: 20px;


  font-weight: 500;
  color: #080B0D;
  cursor: default;
}

.more-btn {
  width: 370px;
  text-align: right;
}

/deep/ #tab-more {
  width: calc(100% - 10px);
  text-align: right;
}

/deep/ .el-tabs__nav {
  width: calc(100% - 200px);
}

/deep/ .el-tabs__content {
  height: 400px;
}
</style>

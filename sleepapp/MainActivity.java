package com.example.fredgogh.sleepapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import lecho.lib.hellocharts.model.AxisValue;

import static com.example.fredgogh.sleepapp.PhotoPickerDialogUtils.alert2;
import static com.example.fredgogh.sleepapp.PhotoPickerDialogUtils.view_custom2;
import static com.example.fredgogh.sleepapp.R.drawable.clock;
import static com.example.fredgogh.sleepapp.R.drawable.coffee;
import static com.example.fredgogh.sleepapp.R.drawable.cycle;
import static com.example.fredgogh.sleepapp.R.drawable.dream;
import static com.example.fredgogh.sleepapp.R.drawable.happy;
import static com.example.fredgogh.sleepapp.R.drawable.pillow;
import static com.example.fredgogh.sleepapp.R.drawable.sleepenvironment;
import static com.example.fredgogh.sleepapp.R.drawable.sleepingposture;
import static com.example.fredgogh.sleepapp.R.drawable.sleepless;
import static com.example.fredgogh.sleepapp.R.drawable.worldsleepday;

public class MainActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private RadioGroup mRadioGroup;
    private RadioButton tab1,tab2,tab3,tab4;  //四个单选按钮
    private List<View> mViews;   //存放视图
    private CardView knowledge_1;
    private CardView knowledge_2;
    private CardView knowledge_3;
    private CardView knowledge_4;
    private CardView knowledge_5;
    private CardView knowledge_6;
    private CardView knowledge_7;
    private CardView knowledge_8;
    private CardView knowledge_9;
    private CardView knowledge_10;
    private CardView sleep;
    private Button setclock;
    private TimePicker timePicker;
    private TextView sleep_endtime;
    private TextView sleep_starttime;
    private TextView sleep_time;
    private TextView averagesleeptime;
    private TextView averagestartsleeptime;
    private TextView averagewakeuptime;
    private TextView situation_output;
    private TextView sleep_start_advice;
    private TextView sleep_end_advice;
    private TextView sleep_time_advice;
    private TextView id;
    private TextView height;
    private TextView weight;
    private TextView age;
    private Button tolinechart1;
    private Button tolinechart2;
    private Button tolinechart3;
    private Button editinfo;
    private Button quit;
    private Button logout;
    private ImageView happy;
    private ImageView low;
    private ImageView sad;
    private ImageView hyper;
    private ImageView sick;
    private ImageView angry;
    private ImageView dizzy;
    private ImageView nervous;
    private ImageView tired;
    private ImageView mood_image;
    public static ImageView profilephoto_image;
    private EditText situation_input;
    private static int mood = 0;
    private static String input;

    public static ArrayList<String> date = new ArrayList<String>(Arrays.asList("5-8","5-9","5-10","5-11","5-12","5-13","5-14"));
    public static ArrayList<Float> sleeptime_data = new ArrayList<Float>(Arrays.asList(60*Float.valueOf("9"),60*Float.valueOf("8.37"),60*Float.valueOf("6.53"),
            60*Float.valueOf("8.2"),60*Float.valueOf("6.7"),60*Float.valueOf("10.8"),60*Float.valueOf("6.5")));
    public static ArrayList<Float> startsleeptime_data = new ArrayList<Float>(Arrays.asList(Float.valueOf("23"),Float.valueOf("23.13"),Float.valueOf("23.77"),
            Float.valueOf("0.1"),Float.valueOf("0.5"),Float.valueOf("22.2"),Float.valueOf("1.1")));
    public static ArrayList<Float> wakeuptime_data = new ArrayList<Float>(Arrays.asList(Float.valueOf("8"),Float.valueOf("7.5"),Float.valueOf("6.3"),
            Float.valueOf("8.3"),Float.valueOf("7.2"),Float.valueOf("9"),Float.valueOf("7.6")));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        initView();//初始化数据
        //对单选按钮进行监听
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i) {
                    case R.id.rb_knowledge:
                        mViewPager.setCurrentItem(0);

                        knowledge_1 = (CardView) findViewById(R.id.knowledge_1);
                        knowledge_1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                AlertDialogUtils.showConfirmDialog(MainActivity.this,"睡眠周期", getDrawable(cycle),"\t人们正常的睡眠结构周期分两个时相：非快速眼动睡眠期（NREM）和快速眼动睡眠期（REM）。NREM与REM交替出现，交替一次称为一个睡眠周期，两种循环往复，每夜通常有 4~5个睡眠周期，每个周期90~110分钟。\n" +
                                        "人类不同年龄的睡眠时间是不同的，婴儿的睡眠时间为20~24小时，幼儿需要9~12小时，学童需要9~10小时，成人需要7~9小时，老年人需要6~8小时，大于80岁的老年人需要9~10小时。");

                            }
                        });
                        knowledge_2 = (CardView) findViewById(R.id.knowledge_2);
                        knowledge_2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                AlertDialogUtils.showConfirmDialog(MainActivity.this,"科学睡眠",getDrawable(clock),
                                        "\t科学睡眠有四大要素，包括睡眠的用具、睡眠的姿势、睡眠的时间和睡眠的环境。\n\t床铺的硬度宜适中，过硬的铺会使人因受其刺激而不得不时常翻身，难以安睡，睡后周身酸痛；枕高一般以睡者的一肩（约10厘米）为宜，过低易造成颈椎生理骨刺。在夏季，枕头要经常翻晒，免让病菌进入口鼻，肺系疾病增多。\n\t有心脏疾患的人，最好多右侧卧，以免造成心脏受压而增加发病机率；脑部因血压高而疼痛者，应适当垫高枕位；肺系病人除垫高枕外，还要经常改换睡侧，以利痰涎排出，胃见胀满和肝胆系疾病者，以右侧位睡眠为宜；四肢有疼痛处者，应力避压迫痛处而卧。\n\t入睡快而睡眠深、一般无梦或少梦者，睡上6小时即可完全恢复精力；入睡慢而浅睡眼多、常多梦恶梦者，即使睡上10小时，仍难精神清爽，应通过各种治疗，以获得有效睡眠，只是延长睡眠时间对身体有害。\n\t在15至24度的温度中，可获得安睡。冬季关门闭窗后吸烟留下的烟雾，以及逸漏的燃烧不全的煤气，也会使人不能安睡。在发射高频电离电磁辐射源附近居注长期睡眠不好而非自身疾病所致者，最好迁徙远处居住。");

                            }
                        });
                        knowledge_3 = (CardView) findViewById(R.id.knowledge_3);
                        knowledge_3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                AlertDialogUtils.showConfirmDialog(MainActivity.this,"如何选择枕头",getDrawable(pillow),"1、睡姿决定枕头的高度\n" +
                                        "\t成人枕头的科学高度是7-9厘米，但这个数据不是绝对的。专家指出，睡姿是影响枕头高低、软硬的决定性因素。如果习惯仰睡，枕头的高度应与自己的拳头高度大致相等;如果喜欢侧睡，那就挑选一款与自己的肩膀厚度相当的枕头;如果习惯脸朝下，把枕头放在胸口睡，就挑选那种轻薄柔软的枕头吧，因为太大的枕头会压迫心脏，造成供血不足。\n" +
                                        "2、皮肤类型决定枕头的材质\n" +
                                        "\t如果皮肤容易过敏，就不要选择羽绒枕，羽绒中的细小纤维容易造成过敏。据统计，全世界有15%的人对羽绒过敏。如果是干性皮肤，建议避免用以中空纤维和人造纤维为填充物的枕头。因为其透气性不好，缺乏弹性，还容易产生静电，尤其是秋季。纤维枕会使皮肤越来越干。\n" +
                                        "3、睡眠质量影响枕头材质的选择\n" +
                                        "\t如果容易失眠，就不要选择稻谷壳糠皮枕头或者慢回弹枕头，因为这些材料的弹性不稳定，稍微移动，就会发出响声，影响睡眠。建议睡眠不好的人选择用高热压缩海绵做成的健康且舒服枕头。因为这样的枕头的首先外形符合人体整体正常生理曲线，使得睡眠时无论仰卧，侧卧，颈椎部位，呼吸道都能恢复平时正常生理曲线，同时它的支撑力和软硬度因为采用了高温下热压缩特制海绵，所以比较合适，符合人体工体学原理，有利于改善失眠。");

                            }
                        });
                        knowledge_4 = (CardView) findViewById(R.id.knowledge_4);
                        knowledge_4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                AlertDialogUtils.showConfirmDialog(MainActivity.this,"遇到失眠怎么办",getDrawable(sleepless),"\t失眠是由于入睡或睡眠持续困难导致的睡眠质量和时间下降，不能满足正常生理和体能恢复的需要，影响正常社会功能的一种主观体验。长期失眠将严重影响大脑和身体的功能，失眠的治疗包括行为治疗和药物治疗两部分。\n" +
                                        "\t1、行为治疗和心理辅导：正确的认识失眠，养成良好的睡眠习惯，预防和矫正不良的睡眠行为和观念，进行心理辅导消除对失眠症状的关注和恐惧。营造合适的入睡环境、减少在床上的时间、睡不着时立即起床、养成无论晚上几点睡每天都在同一时间起床的习惯、减少或取消白天午睡等，选择合适枕头、睡前泡脚放松身心等也会起到辅助作用。另外，避免睡前过饱或饥饿，减少情绪波动。" +
                                        "\t2、用良好的卧具环境促进睡眠：卧室一定要“藏风聚气”，睡床必须摆放在室内最安稳、最明亮且能纵观全室的地方，床位要面朝南北向，床不要正对着门，因为门是气口也是风口，如果正对就会生病。\n" +
                                        "卧室的灯光宜暗不宜明，古人讲“明厅暗室”，这样有利于保证卧室的私密性以及温馨感。\n" +
                                        "\t3、用科学的姿态促进睡眠：“高枕”并不无“忧”，有人曾做过研究，常年枕两个枕头的人，比枕一个枕头的人患颈椎病的发病率高得多的多，过度地高枕睡眠也会导致气道受压、呼吸受阻、给肺的功能造成不良影响，成人应该按照体型胖瘦来确定枕头的高度。侧卧时肩部与头颅的平行高度为枕头的厚度为好。\n" +
                                        "\t4、正确的睡姿能促进睡眠：冬睡不蒙头、夏睡不露腹；裸睡有利于睡眠；老年人宜采取“卧如弓”的睡姿，这种姿态比较放松，有利于消除疲劳，帮助胃消化，同时方便心脏血液回流，减轻心脏负担，避免心脏受压。\n" +
                                        "\t5、药物治疗：若行为治疗失败，可采用药物治疗。常用药物为苯二氮卓类药物（地西泮、艾司唑仑等）和非苯二氮卓类药物（如吡唑嘧啶类、GABA受体激动药及其再摄取抑制药等）以及其他有助于睡眠的药物（如抗抑郁药）等。但要注意药物依赖和停药症状反弹，并遵从个体化和按需用药的原则，遵医嘱用药。\n" +
                                        "\t失眠药物治疗的效果往往有限，因为它并不能解决失眠的根本原因，排除病因，养成良好的睡眠习惯，通过行为治疗改善失眠症状才是治本之法。");

                            }
                        });
                        knowledge_5 = (CardView) findViewById(R.id.knowledge_5);
                        knowledge_5.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                AlertDialogUtils.showConfirmDialog(MainActivity.this,"梦是什么",getDrawable(dream),"\t梦是一种意象语言。《庄子·齐物论》云：“且有大觉，而后知此其大梦也。”这些意象从平常事物到超现实事物都有；事实上，梦常常对艺术等方面激发出灵感，德国化学家凯库勒（Friedrich August Kekulé）宣称梦见一条正在吞食自己尾巴的蛇，而悟出苯环（Benzene）的分子结构。" +
                                        "\n\t梦的产生：人在睡眠时，脑细胞也进入放松和休息状态，但有些脑细胞没有完全休息，微弱的刺激就会引起他们的活动，从而引发梦境。比如，白天有一件事令你特别兴奋，临睡前你还在想着这件事，当大脑其他的神经细胞都休息了，这一部分神经细胞还在兴奋， 你就会做一个内容相似的梦，正所谓“日有所思，夜有所梦”。" +
                                        "\n\t绝大部分的科学家相信所有人类都会做梦，并且在每次睡眠中都会有相同的频率。正如著名心理专家郝滨先生在其著作《催眠与心理压力释放》中阐述：“当人们处于异相睡眠期间，如果将其唤醒，被唤醒的人往往会说他们正在做梦，所以人们一般认为，做梦是异相睡眠的特征之一，研究发现，人人都做梦”。" +
                                        "因此，如果一个人觉得他们没有做梦或者一个夜晚中只做了一个梦，这是因为他们关于那些梦的记忆已经消失了。这种“记忆抹除”的情况通常发生在一个人是自然缓和地从快速眼动睡眠阶段经过慢波睡眠期而进入清醒状态。如果一个人直接从快速动眼睡眠期中被叫醒的话（比如说被闹钟叫醒），他们就比较可能会记得那段快速眼动期所作的梦境（不过并非所有发生在快速眼动期的梦都会被记得，因为每个快速眼动期之间会插入慢波睡眠期，而那会导致前一个梦的记忆消失）。");

                            }
                        });
                        knowledge_6 = (CardView) findViewById(R.id.knowledge_6);
                        knowledge_6.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                AlertDialogUtils.showConfirmDialog(MainActivity.this,"世界睡眠日",getDrawable(worldsleepday),"\t世界睡眠日是每年的3月21日。\n" +
                                        "\t睡眠是人体的一种主动过程，可以恢复精神和解除疲劳。充足的睡眠、均衡的饮食和适当的运动，是国际社会公认的三项健康标准。为唤起全民对睡眠重要性的认识，2001年，国际精神卫生和神经科学基金会主办的全球睡眠和健康计划发起了一项全球性的活动，此项活动的重点在于引起人们对睡眠重要性和睡眠质量的关注。\n" +
                                        "\t2022年世界睡眠日中国主题是：良好睡眠，健康同行。进入21世纪，人们的健康意识空前提高，“拥有健康才能有一切”的新理念深入人心，因此有关睡眠问题引起了国际社会的关注。人一生中有三分之一的时间是在睡眠中度过，五天不睡眠人就会死去，可见睡眠是人的生理需要。睡眠作为生命所必须的过程，是机体复原、整合和巩固记忆的重要环节，是健康不可缺少的组成部分。\n"+
                                        "\t进入21世纪，人们的健康意识空前提高，“拥有健康才能有一切”的新理念深入人心，因此有关睡眠问题引起了国际社会的关注。人一生中有三分之一的时间是在睡眠中度过，五天不睡眠人就会死去，可见睡眠是人的生理需要。睡眠作为生命所必须的过程，是机体复原、整合和巩固记忆的重要环节，是健康不可缺少的组成部分。"+
                                        "\t世界卫生组织调查，27%的人有睡眠问题。为唤起全民对睡眠重要性的认识，国际精神卫生组织主办的全球睡眠和健康计划于2001年发起了一项全球性的活动――将每年的3月21日，即春季的第一天定为“世界睡眠日”。\n" +
                                        "\t据中国6城市的市场调研显示，成年人一年内的失眠患病率高达57%。中国医师协会精神科医师分会与赛诺菲－安万特合作的“各科医师睡眠状况调查”得出结果，该调查对全国30家医院的1914名医生进行了调查，结果显示，在2002年内有69.4%的医生存在睡眠障碍问题。2003年中国睡眠研究会把“世界睡眠日”正式引入中国。"
                                );

                            }
                        });
                        knowledge_7 = (CardView) findViewById(R.id.knowledge_7);
                        knowledge_7.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                AlertDialogUtils.showConfirmDialog(MainActivity.this,"睡眠姿势",getDrawable(sleepingposture),
                                        "平卧\n" +
                                        "\t生活中,选择平卧睡姿的人群占比较大的比重,也是经常被推荐的一种姿势。这种睡眠姿势不会压迫体内脏腑等器官,还可以让脊椎部位成一条线，能有效缓解颈部、背部酸痛的症状。但这种睡姿容易导致舌根下坠、阻塞呼吸,不适合经常打呼噜或是有呼吸道疾病的人群。\n" +
                                        "\n仰卧\n" +
                                        "\t近些年，随着昂首床垫即护脊床垫的出现，又增加了一种仰卧的睡姿。因为昂首床垫的头部是斜的，所以仰卧是不同于平卧的，它是能让你的脊柱更加后仰的睡姿，这能起到矫正脊柱的作用。当然， 最好是根据自己的情况仰卧二至五个小时，然后再放上昂首床垫枕头采用平卧睡觉。当然，刚开始使用昂首床垫时可以时间短一些，如一个小时左右。" +
                                        "\n左侧卧\n" +
                                        "\t左侧卧虽然看上去很舒服,有利于身体放松,消除疲劳,但因为心脏位于左胸腔,这种睡姿很容易压迫心脏,若想保证全身的血液循环,会加重心脏负担。所以,有心脏疾病的人或老年人不建议采用这种睡姿。\n" +
                                        "\n右侧卧\n" +
                                        "\t右侧卧的睡姿是大多数人比较认可的一种健康姿势,会使人的睡眠有稳定感,且有利于胃肠道的正常运行,可促进消化,还不会压迫心脏。但是,这种睡姿其实也有弊端,就是会影响右侧肺部运动,如果有肺气肿或是肺部不太好的人,最好不要选择右侧卧。\n" +
                                        "\n俯卧\n" +
                                        "\t很明显,俯卧是比较不健康的一种睡姿。因为人在趴着的时候,会使胸部受到压迫,导致出现心脏不适、呼吸困难的情况。而且,这种睡姿很难让脊柱保持中央位置,会给关节和肌肉带来压力,刺激神经,时间久了,腰背就会产生疼痛、麻木的感觉。而且,俯卧时颈部向侧面扭转,头部歪向一边,很容易导致颈肌受损。所以,一般不推荐采取这种睡姿。"
                                );

                            }
                        });
                        knowledge_8 = (CardView) findViewById(R.id.knowledge_8);
                        knowledge_8.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                AlertDialogUtils.showConfirmDialog(MainActivity.this,"助眠音乐",getDrawable(sleepingposture),
                                        "其一，避免有歌词的音乐。\n" +
                                                "\t因为它们很可能会激发我们的大脑进行更多的活动，抒情的文案往往会提醒我们生活中的人或事件，这可能会引发焦虑或沉思。" +
                                                "建议听器乐，尤其是那些依靠大提琴或低音提琴，避免任何乐观、夸张或使用高音调的乐器（如小提琴或长笛）。"+
                                                "其二，节奏也很重要。\n" +
                                                "\t建议使用“节奏慢的简单音乐”，尤其是60至80 BPM的歌曲。有一首歌被誉为最轻松的歌曲：英国乐队Marconi Union的“Weightless”。在Mindlab International进行的研究中，听这首歌的参与者体验到“总体焦虑降低了65％”。其实这首歌是被设计出来的：乐队与声音治疗师合作创作了这首频率为60 BPM的歌曲，它的实际目的是“降低听众的心率，降低血压并降低压力”。\n" +
                                                "其三，也可以选择白噪音。\n" +
                                                "\t你可能听说过白噪音，它包含所有强度相同的可用声音频率，对于掩盖破坏性的环境噪声非常有用。\n"+
                                                "\t还有粉红色噪音，与白色噪声相似，但强度随着频率的增加而降低。这使它听起来更舒缓，就像一首歌有很多大提琴或低音一样。"
                                );

                            }
                        });
                        knowledge_9 = (CardView) findViewById(R.id.knowledge_9);
                        knowledge_9.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                AlertDialogUtils.showConfirmDialog(MainActivity.this,"睡眠环境",getDrawable(sleepenvironment),
                                    "1、相对安静" +
                                            "\n\t嘈杂的环境肯定是不利于睡眠的，但若通过隔音获得绝对安静的环境也会影响睡眠。进化过程使得人类更习惯于在接近自然的环境下入睡。如果你能隐隐约约听到有几只蛐蛐在叫，那么这个环境是最适合睡眠的。\n" +
                                            "2、相对低温" +
                                            "\n\t人在睡眠时需要的温度会比白天略低一些，所以相对低温更有利于睡眠。\n" +
                                            "3、光线要暗" +
                                            "\n\t开灯睡觉是不可取的，即使能睡着也会影响睡眠质量。另外，在早晨由阳光唤醒是最佳的“闹铃”。\n" +
                                            "4、合适的湿度" +
                                            "\n\t一般来讲，相对湿度在60%～70%最有利于睡眠，当然，不同人群会略有差异。\n" +
                                            "5、睡觉时穿着" +
                                            "\n\t切不可穿过紧的睡衣，戴眼罩、耳塞也会使身体无法完全放松，从而影响睡眠。保证睡眠时身体能够尽可能放松是最有利于睡眠的。另外，睡眠时以“头南脚北”的方向最佳。"
                                );

                            }
                        });
                        knowledge_10 = (CardView) findViewById(R.id.knowledge_10);
                        knowledge_10.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                AlertDialogUtils.showConfirmDialog(MainActivity.this,"喝咖啡为什么能提神",getDrawable(coffee),
                                        "\t咖啡之所以能够达到比较明显的提神效果，是因为它里面所含有的物质“咖啡因”属于一种带有兴奋剂性质的生物碱，而咖啡中的咖啡因含量比茶叶要高出一倍左右，所以人们喝完咖啡后往往会感到精神振奋、睡意全无、动作灵活、无比轻松。" +
                                                "\n\t咖啡所含咖啡因是生物碱的一种，是徐缓兴奋剂。它的兴奋性比茶叶更优，所含咖啡因比茶叶高出40％～60％，能兴奋大脑神经细胞、提高脑细胞活力、促进思维联想和分析判断能力，可提神醒脑，使人精神振奋，消除疲劳和睡意，改善动作灵活性，使人有轻松的感觉。" +
                                                "\n\t但是，长期饮用咖啡，会使人体对咖啡产生依赖性(上瘾），干扰大脑对刺激性物质的选择作用。一旦停饮后，会导致大脑高度抑制、血压降低、失眠、焦虑、神经衰弱等症状，有的甚至出现精神异常。不宜无节制地滥饮。饮咖啡还要注意适量，一杯较浓的咖啡在机体内维持作用的时间为4-5小时。因此，既不能连续饮用，在两次饮用之间也要保持一定的间隔时间，更不宜在睡前饮用。"
                                );

                            }
                        });


                        break;

                    //页面2
                    case R.id.rb_report:
                        mViewPager.setCurrentItem(1);
                        sleep = (CardView)findViewById(R.id.sleep);
                        setclock = (Button)findViewById(R.id.setclock);
                        timePicker = (TimePicker)findViewById(R.id.tp);
                        happy = (ImageView)findViewById(R.id.happy);
                        low = (ImageView)findViewById(R.id.low);
                        sad = (ImageView)findViewById(R.id.sad);
                        hyper = (ImageView)findViewById(R.id.hyper);
                        angry = (ImageView)findViewById(R.id.angry);
                        sick = (ImageView)findViewById(R.id.sick);
                        dizzy = (ImageView)findViewById(R.id.dizzy);
                        tired = (ImageView)findViewById(R.id.tired);
                        nervous = (ImageView)findViewById(R.id.nervous);
                        situation_input = (EditText)findViewById(R.id.situation_input);

                        happy.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                happy.setImageResource((R.drawable.happy_selected));
                                low.setImageResource((R.drawable.low));
                                sad.setImageResource((R.drawable.sad));
                                hyper.setImageResource((R.drawable.hyper));
                                sick.setImageResource((R.drawable.sick));
                                dizzy.setImageResource((R.drawable.dizzy));
                                tired.setImageResource((R.drawable.tired));
                                nervous.setImageResource((R.drawable.nervous));
                                angry.setImageResource((R.drawable.angry));
                                mood = R.drawable.happy;
                            }
                        });
                        low.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                happy.setImageResource((R.drawable.happy));
                                low.setImageResource((R.drawable.low_selected));
                                sad.setImageResource((R.drawable.sad));
                                hyper.setImageResource((R.drawable.hyper));
                                sick.setImageResource((R.drawable.sick));
                                dizzy.setImageResource((R.drawable.dizzy));
                                tired.setImageResource((R.drawable.tired));
                                nervous.setImageResource((R.drawable.nervous));
                                angry.setImageResource((R.drawable.angry));
                                mood = R.drawable.low;
                            }
                        });
                        sad.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                happy.setImageResource((R.drawable.happy));
                                low.setImageResource((R.drawable.low));
                                sad.setImageResource((R.drawable.sad_selected));
                                hyper.setImageResource((R.drawable.hyper));
                                sick.setImageResource((R.drawable.sick));
                                dizzy.setImageResource((R.drawable.dizzy));
                                tired.setImageResource((R.drawable.tired));
                                nervous.setImageResource((R.drawable.nervous));
                                angry.setImageResource((R.drawable.angry));
                                mood = R.drawable.sad;
                            }
                        });
                        hyper.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                happy.setImageResource((R.drawable.happy));
                                low.setImageResource((R.drawable.low));
                                sad.setImageResource((R.drawable.sad));
                                hyper.setImageResource((R.drawable.hyper_selected));
                                sick.setImageResource((R.drawable.sick));
                                dizzy.setImageResource((R.drawable.dizzy));
                                tired.setImageResource((R.drawable.tired));
                                nervous.setImageResource((R.drawable.nervous));
                                angry.setImageResource((R.drawable.angry));
                                mood = R.drawable.hyper;
                            }
                        });
                        sick.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                happy.setImageResource((R.drawable.happy));
                                low.setImageResource((R.drawable.low));
                                sad.setImageResource((R.drawable.sad));
                                hyper.setImageResource((R.drawable.hyper));
                                sick.setImageResource((R.drawable.sick_selected));
                                dizzy.setImageResource((R.drawable.dizzy));
                                tired.setImageResource((R.drawable.tired));
                                nervous.setImageResource((R.drawable.nervous));
                                angry.setImageResource((R.drawable.angry));
                                mood = R.drawable.sick;
                            }
                        });
                        dizzy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            happy.setImageResource((R.drawable.happy));
                            low.setImageResource((R.drawable.low));
                            sad.setImageResource((R.drawable.sad));
                            hyper.setImageResource((R.drawable.hyper));
                            sick.setImageResource((R.drawable.sick));
                            dizzy.setImageResource((R.drawable.dizzy_selected));
                            tired.setImageResource((R.drawable.tired));
                            nervous.setImageResource((R.drawable.nervous));
                            angry.setImageResource((R.drawable.angry));
                            mood = R.drawable.dizzy;
                        }
                    });
                        tired.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            happy.setImageResource((R.drawable.happy));
                            low.setImageResource((R.drawable.low));
                            sad.setImageResource((R.drawable.sad));
                            hyper.setImageResource((R.drawable.hyper));
                            sick.setImageResource((R.drawable.sick));
                            dizzy.setImageResource((R.drawable.dizzy));
                            tired.setImageResource((R.drawable.tired_selected));
                            nervous.setImageResource((R.drawable.nervous));
                            angry.setImageResource((R.drawable.angry));
                            mood = R.drawable.tired;
                        }
                    });
                        nervous.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            happy.setImageResource((R.drawable.happy));
                            low.setImageResource((R.drawable.low));
                            sad.setImageResource((R.drawable.sad));
                            hyper.setImageResource((R.drawable.hyper));
                            sick.setImageResource((R.drawable.sick));
                            dizzy.setImageResource((R.drawable.dizzy));
                            tired.setImageResource((R.drawable.tired));
                            nervous.setImageResource((R.drawable.nervous_selected));
                            angry.setImageResource((R.drawable.angry));
                            mood = R.drawable.nervous;
                        }
                    });
                        angry.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                happy.setImageResource((R.drawable.happy));
                                low.setImageResource((R.drawable.low));
                                sad.setImageResource((R.drawable.sad));
                                hyper.setImageResource((R.drawable.hyper));
                                sick.setImageResource((R.drawable.sick));
                                dizzy.setImageResource((R.drawable.dizzy));
                                tired.setImageResource((R.drawable.tired));
                                nervous.setImageResource((R.drawable.nervous));
                                angry.setImageResource((R.drawable.angry_selected));
                                mood = R.drawable.angry;
                            }
                        });
                        situation_input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                            @Override
                            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                                input = situation_input.getText().toString();
                                return false;
                            }
                        });




                        sleep.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(MainActivity.this,SleepActivity.class);
                                startActivity(intent);
                            }
                        });
                        setclock.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                                intent.putExtra(AlarmClock.EXTRA_HOUR, timePicker.getHour());
                                intent.putExtra(AlarmClock.EXTRA_MINUTES, timePicker.getMinute());
                                intent.putExtra(AlarmClock.EXTRA_VIBRATE, true);
                                intent.putExtra(AlarmClock.EXTRA_SKIP_UI, true);
                                startActivity(intent);
                                Toast.makeText(MainActivity.this,"闹钟已设置",Toast.LENGTH_LONG).show();
                            }
                        });

                        break;

                    //页面3
                    case R.id.rb_check:
                        mViewPager.setCurrentItem(2);
                        final Intent intent = getIntent();
                        sleep_endtime = (TextView)findViewById(R.id.sleep_endtime);
                        sleep_starttime = (TextView)findViewById(R.id.sleep_starttime);
                        sleep_time = (TextView)findViewById(R.id.sleep_time);
                        averagesleeptime = (TextView)findViewById(R.id.averagesleeptime);
                        averagestartsleeptime = (TextView)findViewById(R.id.averagestartsleeptime);
                        averagewakeuptime = (TextView)findViewById(R.id.averagewakeuptime);
                        mood_image = (ImageView)findViewById(R.id.mood);
                        situation_output = (TextView)findViewById(R.id.situation_output);
                        sleep_start_advice = (TextView)findViewById(R.id.sleep_start_advice);
                        sleep_end_advice = (TextView)findViewById(R.id.sleep_end_advice);
                        sleep_time_advice = (TextView)findViewById(R.id.sleep_time_advice);


                        //初始化各项数据
                        sleep_starttime.setText(Math.round(Math.floor(startsleeptime_data.get(startsleeptime_data.size()-1)))+":"+Math.round(60*(startsleeptime_data.get(startsleeptime_data.size()-1)-Math.floor(startsleeptime_data.get(startsleeptime_data.size()-1)))));
                        sleep_endtime.setText(Math.round(Math.floor(wakeuptime_data.get(wakeuptime_data.size()-1)))+":"+Math.round(60*(wakeuptime_data.get(wakeuptime_data.size()-1)-Math.floor(wakeuptime_data.get(wakeuptime_data.size()-1)))));
                        sleep_time.setText(Math.round(Math.floor(sleeptime_data.get(sleeptime_data.size()-1)/60))+"h  "+ Math.round((sleeptime_data.get(sleeptime_data.size()-1)-60*Math.floor(sleeptime_data.get(sleeptime_data.size()-1)/60))) +"min");
                        averagestartsleeptime.setText(Math.round(Math.floor(averagetime(startsleeptime_data)))+":"+Math.round(60*(averagetime(startsleeptime_data)-Math.floor(averagetime(startsleeptime_data)))));
                        averagewakeuptime.setText(Math.round(Math.floor(averagetime(wakeuptime_data)))+":"+Math.round(60*(averagetime(wakeuptime_data)-Math.floor(averagetime(wakeuptime_data)))));
                        averagesleeptime.setText(Math.round(Math.floor(average(sleeptime_data))/60)+"h  "+ Math.round((average(sleeptime_data)-60*Math.floor(average(sleeptime_data)/60))) +"min");
                        setsleepstarttimeadvice(startsleeptime_data.get(startsleeptime_data.size()-1));
                        setsleependtimeadvice(wakeuptime_data.get(wakeuptime_data.size()-1));
                        setsleeptimeadvice(sleeptime_data.get(sleeptime_data.size()-1));

                        //接收本次睡眠数据
                        if(intent.getIntExtra("sleep_hour",25)!=25 && intent.getIntExtra("sleep_minute",61)!=61) {
                            sleep_starttime.setText(intent.getIntExtra("sleep_hour",25) + ":" + intent.getIntExtra("sleep_minute",61));
                            setsleepstarttimeadvice(intent.getIntExtra("sleep_hour",25) + new Float(intent.getIntExtra("sleep_minute",61))/60);
                            if(intent.getIntExtra("startsleep_date",0) == intent.getIntExtra("wakeup_date",0)){
                                if(!date.contains(Calendar.getInstance().get(Calendar.MONTH)+1 +"-"+Calendar.getInstance().get(Calendar.DAY_OF_MONTH))){
                                    date.add((Calendar.getInstance().get(Calendar.MONTH)+1) +"-"+(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)-1));
                                    startsleeptime_data.add(intent.getIntExtra("sleep_hour",25) + new Float(intent.getIntExtra("sleep_minute",61))/60);
                                    //Toast.makeText(MainActivity.this,intent.getIntExtra("sleep_hour",25) + new Float(intent.getIntExtra("sleep_minute",61))/60+"",Toast.LENGTH_LONG).show();
                                }
                            }else{
                                if(!date.contains(Calendar.getInstance().get(Calendar.MONTH)+1 +"-"+Calendar.getInstance().get(Calendar.DAY_OF_MONTH))){
                                    date.add((Calendar.getInstance().get(Calendar.MONTH)+1) +"-"+(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)));
                                    startsleeptime_data.add(intent.getIntExtra("sleep_hour",25) + new Float(intent.getIntExtra("sleep_minute",61))/60);
                                    //Toast.makeText(MainActivity.this,intent.getIntExtra("sleep_hour",25) + new Float(intent.getIntExtra("sleep_minute",61))/60+"",Toast.LENGTH_LONG).show();
                                }
                            }

                        }
                        if (intent.getIntExtra("wakeup_hour",25) != 25 && intent.getIntExtra("wakeup_minute",61) != 61) {
                            sleep_endtime.setText(intent.getIntExtra("wakeup_hour",25) + ":" + intent.getIntExtra("wakeup_minute",61));
                            setsleependtimeadvice(intent.getIntExtra("wakeup_hour",25) + new Float(intent.getIntExtra("wakeup_minute",61))/60);
                            wakeuptime_data.add(intent.getIntExtra("wakeup_hour",25) + new Float(intent.getIntExtra("wakeup_minute",61))/60);
                            if (intent.getIntExtra("startsleep_date",0) == intent.getIntExtra("wakeup_date",0)){
                                Float sleeptimebyminute = intent.getIntExtra("wakeup_hour",25)*60 + new Float(intent.getIntExtra("wakeup_minute",61)) - intent.getIntExtra("sleep_hour",25)*60 - new Float(intent.getIntExtra("sleep_minute",61));
                                setsleeptimeadvice(sleeptimebyminute);
                                sleeptime_data.add(sleeptimebyminute);
                                sleep_time.setText(Math.round(Math.floor(sleeptimebyminute/60))+"h  "+Math.round(sleeptimebyminute-60*Math.floor(sleeptimebyminute/60))+"min");
                            }else{
                                Float sleeptimebyminute = 24*60 - intent.getIntExtra("sleep_hour",25)*60 - new Float(intent.getIntExtra("sleep_minute",61)) + intent.getIntExtra("wakeup_hour",25)*60 + new Float(intent.getIntExtra("wakeup_minute",61));
                                setsleeptimeadvice(sleeptimebyminute);
                                sleeptime_data.add(sleeptimebyminute);
                                sleep_time.setText(Math.round(Math.floor(sleeptimebyminute/60))+"h  "+Math.round(sleeptimebyminute-60*Math.floor(sleeptimebyminute/60))+"min");
                            }
                            averagestartsleeptime.setText(Math.round(Math.floor(averagetime(startsleeptime_data)))+":"+Math.round(60*(averagetime(startsleeptime_data)-Math.floor(averagetime(startsleeptime_data)))));
                            averagewakeuptime.setText(Math.round(Math.floor(averagetime(wakeuptime_data)))+":"+Math.round(60*(averagetime(wakeuptime_data)-Math.floor(averagetime(wakeuptime_data)))));
                            averagesleeptime.setText(Math.round(Math.floor(average(sleeptime_data))/60)+"h  "+ Math.round((average(sleeptime_data)-60*Math.floor(average(sleeptime_data)/60))) +"min");
                            intent.removeExtra("sleep_hour");
                            intent.removeExtra("sleep_minute");
                            intent.removeExtra("wakeup_hour");
                            intent.removeExtra("sleep_minute");
                            intent.removeExtra("startsleep_date");
                            intent.removeExtra("wakeup_date");
                        }
                        if (mood != 0){
                            mood_image.setImageResource(mood);
                        }

                        if  (input != null){
                            situation_output.setText(input);
                        }

                        //打开折线图
                        tolinechart1 = (Button)findViewById(R.id.tolinechart1);
                        tolinechart2 = (Button)findViewById(R.id.tolinechart2);
                        tolinechart3 = (Button)findViewById(R.id.tolinechart3);

                        tolinechart1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(MainActivity.this,linechartActivity1.class);
                                startActivity(intent);
                            }
                        });
                        tolinechart2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(MainActivity.this,linechartActivity2.class);
                                startActivity(intent);
                            }
                        });
                        tolinechart3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(MainActivity.this,linechartActivity3.class);
                                startActivity(intent);
                            }
                        });
                        break;

                    //页面4
                    case R.id.rb_me:
                        mViewPager.setCurrentItem(3);
                        editinfo = (Button)findViewById(R.id.editinfo);
                        id = (TextView)findViewById(R.id.info_id);
                        age = (TextView)findViewById(R.id.age);
                        height = (TextView)findViewById(R.id.height);
                        weight = (TextView)findViewById(R.id.weight);
                        quit = (Button)findViewById(R.id.quit);
                        logout = (Button)findViewById(R.id.logout);
                        profilephoto_image = (ImageView)findViewById(R.id.profilephoto);

                        //初始化
                        fillin();

                        profilephoto_image.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                PhotoPickerDialogUtils.showConfirmDialog(MainActivity.this);
                                clicks();
                            }
                        });

                        editinfo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(MainActivity.this,EditinfoActivity.class);
                                startActivity(intent);
                            }
                        });

                        quit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
                                SharedPreferences.Editor editor = sp.edit();
                                String user_id = sp.getString("loginUserName",null);
                                editor.putBoolean("autologin",false).commit();
                                Toast.makeText(MainActivity.this,user_id + "已退出",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                                startActivity(intent);
                            }
                        });

                        logout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
                                SharedPreferences sp_age = getSharedPreferences("age",MODE_PRIVATE);
                                SharedPreferences sp_height = getSharedPreferences("height",MODE_PRIVATE);
                                SharedPreferences sp_weight = getSharedPreferences("weight",MODE_PRIVATE);
                                SharedPreferences.Editor editor = sp.edit();
                                SharedPreferences.Editor editor_age = sp_age.edit();
                                SharedPreferences.Editor editor_height = sp_height.edit();
                                SharedPreferences.Editor editor_weight = sp_weight.edit();
                                String user_id = sp.getString("loginUserName",null);

                                editor.remove(user_id).commit();
                                editor_age.remove(user_id).commit();
                                editor_height.remove(user_id).commit();
                                editor_weight.remove(user_id).commit();
                                editor.putBoolean("autologin",false).commit();
                                editor.putBoolean("rememberid",false).commit();
                                Toast.makeText(MainActivity.this,user_id + "已注销",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                                startActivity(intent);
                            }
                        });
                        break;
                }
            }
        });

    }


    private void initView() {
        //初始化控件
        mViewPager=(ViewPager)findViewById(R.id.viewpager);
        mRadioGroup=(RadioGroup) findViewById(R.id.rg_tab);
        tab1=(RadioButton) findViewById(R.id.rb_knowledge);
        tab2= (RadioButton) findViewById(R.id.rb_report);
        tab3= (RadioButton) findViewById(R.id.rb_check);
        tab4= (RadioButton) findViewById(R.id.rb_me);

        mViews=new ArrayList<View>();//加载，添加视图
        mViews.add(LayoutInflater.from(this).inflate(R.layout.knowledge,null));
        mViews.add(LayoutInflater.from(this).inflate(R.layout.report,null));
        mViews.add(LayoutInflater.from(this).inflate(R.layout.check,null));
        mViews.add(LayoutInflater.from(this).inflate(R.layout.me,null));


        mViewPager.setAdapter(new MyViewPagerAdapter());//设置一个适配器
        //对viewpager监听，让分页和底部图标保持一起滑动
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override   //让viewpager滑动的时候，下面的图标跟着变动
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        tab1.setChecked(true);
                        tab2.setChecked(false);
                        tab3.setChecked(false);
                        tab4.setChecked(false);
                        break;
                    case 1:
                        tab1.setChecked(false);
                        tab2.setChecked(true);
                        tab3.setChecked(false);
                        tab4.setChecked(false);
                        break;
                    case 2:
                        tab1.setChecked(false);
                        tab2.setChecked(false);
                        tab3.setChecked(true);
                        tab4.setChecked(false);
                        break;
                    case 3:
                        tab1.setChecked(false);
                        tab2.setChecked(false);
                        tab3.setChecked(false);
                        tab4.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //ViewPager适配器
    private class MyViewPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mViews.size();
        }


        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(mViews.get(position));
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(mViews.get(position));
            return mViews.get(position);


        }

    }

    //平均时长计算
    public Float average(ArrayList<Float> arrayList) {
        float sum = 0;
        for (int i = 0; i < arrayList.size(); i++) {
            sum += arrayList.get(i);
        }
        float avg = sum / arrayList.size();
        return avg;
    }

    //平均时间计算
    public Float averagetime(ArrayList<Float> arrayList) {
        float sum = 0;
        for (int i = 0; i < arrayList.size(); i++) {
            if(arrayList.get(i)<12){
                sum += arrayList.get(i);
            }else{
                sum += arrayList.get(i)-24;
            }
        }
        float avg = sum / arrayList.size();
        if(avg > 0){
        return avg;
        } else{
        return 24+avg;
        }
    }

    //分情况给出入睡时间建议
    public void setsleepstarttimeadvice(Float sleepstarttime){
        if(sleepstarttime >23 || sleepstarttime <8){
            sleep_start_advice.setText("入睡时间较晚。\n晚于23：00入睡，会造成睡眠质量下降，导致精力不充沛。此外，入睡时间过晚还会对心血管产生不良影响。");
        }
        if (sleepstarttime>22 && sleepstarttime<23){
            sleep_start_advice.setText("入睡时间健康。\n22：00-23：00入睡对人体健康最有益。牛津大学研究团队的研究显示，睡眠开始时间与心血管患病风险呈现“U”型关系：22-23点入睡，患心血管疾病的风险最小；而入睡过早或过晚都会增加患病风险。");

        }
        if (sleepstarttime<18 && sleepstarttime>8){
            sleep_start_advice.setText("非正常睡眠时间。\n若为午睡，应选择午饭后半小时左右,睡20-30分钟为宜。");
        }
        if (sleepstarttime<22 &&sleepstarttime>18){
            sleep_start_advice.setText("入睡时间较早。\n研究表明，过早入睡和过晚入睡都不利于身体健康，过早睡眠，人体生物钟还未到达低潮阶段，可能导致失眠问题。");
        }
    }

    //分情况给出醒来时间建议
    public void setsleependtimeadvice(Float sleependtime){
        if(sleependtime > 6 && sleependtime < 8 ){
            sleep_end_advice.setText("起床时间健康。\n一般认为，6:00-8:00是最佳起床时间，“一日之计在于晨”，此时人体器官在得到充分休息后开始活跃，人也会感到精神饱满。");
        }
        if (sleependtime < 6){
            sleep_end_advice.setText("起床时间较早。\n过早起床，人体还未得到充分休息，人会感到疲惫，精神状态较差；同时，过早起床的人体内皮质醇水平更高，这是一种与压力有关的荷尔蒙，会使人更容易肌肉疼痛、头痛，也更容易烦躁和疲惫。");

        }
        if (sleependtime > 12){
            sleep_end_advice.setText("非正常起床时间。\n若为午睡。建议在14：00前起床。");

        }
        if (sleependtime > 8  && sleependtime < 12){
            sleep_end_advice.setText("起床时间较晚。\n睡懒觉虽然可以更多地休息来补充精神，但也会对人体产生负面影响。过晚起床会打乱人体生物钟节律，影响人体内分泌活动；影响胃肠道功能，影响消化；降低注意力、兴奋性和记忆力，影响正常工作。");
        }
    }

    //分情况给出醒来时间建议
    public void setsleeptimeadvice(Float sleeptimebyminute){
        if(sleeptimebyminute > 7*60 && sleeptimebyminute < 8*60){
            sleep_time_advice.setText("睡眠时长健康。\n一般认为，7-8小时是最佳睡眠时长，经过7-8小时的睡眠，人体能够得到充分的休息，获得良好的精神状态，提升工作效率，还可以增强免疫力。");
        }
        if (sleeptimebyminute < 7*60){
            sleep_time_advice.setText("睡眠时长不足。\n睡眠时长不足首先影响人的精神状况，得不到良好的休息，日常工作效率就会下降，还可能对身体健康产生非常不好的影响，导致机体内分泌紊乱、记忆力衰退等问题。");
        }
        if (sleeptimebyminute > 8*60){
            sleep_time_advice.setText("睡眠时长过多。\n睡眠时长过长，会影响大脑细胞活性，导致注意力无法集中、头昏脑涨、乏力、记忆力下降等问题。");
        }
    }

    private void fillin(){
        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
        SharedPreferences sp_age = getSharedPreferences("age",MODE_PRIVATE);
        SharedPreferences sp_height = getSharedPreferences("height",MODE_PRIVATE);
        SharedPreferences sp_weight = getSharedPreferences("weight",MODE_PRIVATE);
        SharedPreferences sp_profilephoto = getSharedPreferences("profilephoto",MODE_PRIVATE);
        String user_id = sp.getString("loginUserName",null);
        id.setText(user_id);
        profilephoto_image.setImageResource(sp_profilephoto.getInt(user_id,R.drawable.happy));
        if (sp_age.getString(user_id,null) != null) {
            age.setText(sp_age.getString(user_id, null) + "岁");
        }
        if(sp_height.getString(user_id,null) != null) {
            height.setText(sp_height.getString(user_id, null) + "cm");
        }
        if (sp_weight.getString(user_id,null) != null) {
            weight.setText(sp_weight.getString(user_id, null) + "kg");
        }
    }

    public void clicks(){
        SharedPreferences sp_profilephoto =getSharedPreferences("profilephoto",MODE_PRIVATE);
        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
        final SharedPreferences.Editor editor_profilephoto = sp_profilephoto.edit();
        final String user_id = sp.getString("loginUserName",null);
        view_custom2.findViewById(R.id.profilephoto_happy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profilephoto_image.setImageResource(R.drawable.happy);
                editor_profilephoto.putInt(user_id,R.drawable.happy).commit();
                alert2.dismiss();
            }
        });
        view_custom2.findViewById(R.id.profilephoto_angry).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profilephoto_image.setImageResource(R.drawable.angry);
                editor_profilephoto.putInt(user_id,R.drawable.angry).commit();
                alert2.dismiss();
            }
        });
        view_custom2.findViewById(R.id.profilephoto_hyper).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profilephoto_image.setImageResource(R.drawable.hyper);
                editor_profilephoto.putInt(user_id,R.drawable.hyper).commit();
                alert2.dismiss();
            }
        });
        view_custom2.findViewById(R.id.profilephoto_sick).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profilephoto_image.setImageResource(R.drawable.sick);
                editor_profilephoto.putInt(user_id,R.drawable.sick).commit();
                alert2.dismiss();
            }
        });
        view_custom2.findViewById(R.id.profilephoto_tired).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profilephoto_image.setImageResource(R.drawable.tired);
                editor_profilephoto.putInt(user_id,R.drawable.tired).commit();
                alert2.dismiss();
            }
        });
        view_custom2.findViewById(R.id.profilephoto_sad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profilephoto_image.setImageResource(R.drawable.sad);
                editor_profilephoto.putInt(user_id,R.drawable.sad).commit();
                alert2.dismiss();
            }
        });
        view_custom2.findViewById(R.id.profilephoto_low).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profilephoto_image.setImageResource(R.drawable.low);
                editor_profilephoto.putInt(user_id,R.drawable.low).commit();
                alert2.dismiss();
            }
        });
        view_custom2.findViewById(R.id.profilephoto_dizzy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profilephoto_image.setImageResource(R.drawable.dizzy);
                editor_profilephoto.putInt(user_id,R.drawable.dizzy).commit();
                alert2.dismiss();
            }
        });
        view_custom2.findViewById(R.id.profilephoto_nervous).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profilephoto_image.setImageResource(R.drawable.nervous);
                editor_profilephoto.putInt(user_id,R.drawable.nervous).commit();
                alert2.dismiss();
            }
        });
        view_custom2.findViewById(R.id.profilephoto_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profilephoto_image.setImageResource(R.drawable.profilephoto1);
                editor_profilephoto.putInt(user_id,R.drawable.profilephoto1).commit();
                alert2.dismiss();
            }
        });
        view_custom2.findViewById(R.id.profilephoto_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profilephoto_image.setImageResource(R.drawable.profilephoto2);
                editor_profilephoto.putInt(user_id,R.drawable.profilephoto2).commit();
                alert2.dismiss();
            }
        });
        view_custom2.findViewById(R.id.profilephoto_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profilephoto_image.setImageResource(R.drawable.profilephoto3);
                editor_profilephoto.putInt(user_id,R.drawable.profilephoto3).commit();
                alert2.dismiss();
            }
        });
        view_custom2.findViewById(R.id.profilephoto_4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profilephoto_image.setImageResource(R.drawable.profilephoto4);
                editor_profilephoto.putInt(user_id,R.drawable.profilephoto4).commit();
                alert2.dismiss();
            }
        });
        view_custom2.findViewById(R.id.profilephoto_5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profilephoto_image.setImageResource(R.drawable.profilephoto5);
                editor_profilephoto.putInt(user_id,R.drawable.profilephoto5).commit();
                alert2.dismiss();
            }
        });
        view_custom2.findViewById(R.id.profilephoto_6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profilephoto_image.setImageResource(R.drawable.profilephoto6);
                editor_profilephoto.putInt(user_id,R.drawable.profilephoto6).commit();
                alert2.dismiss();
            }
        });
        view_custom2.findViewById(R.id.profilephoto_7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profilephoto_image.setImageResource(R.drawable.profilephoto7);
                editor_profilephoto.putInt(user_id,R.drawable.profilephoto7).commit();
                alert2.dismiss();
            }
        });
        view_custom2.findViewById(R.id.profilephoto_8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profilephoto_image.setImageResource(R.drawable.profilephoto8);
                editor_profilephoto.putInt(user_id,R.drawable.profilephoto8).commit();
                alert2.dismiss();
            }
        });
        view_custom2.findViewById(R.id.profilephoto_9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profilephoto_image.setImageResource(R.drawable.profilephoto9);
                editor_profilephoto.putInt(user_id,R.drawable.profilephoto9).commit();
                alert2.dismiss();
            }
        });
        view_custom2.findViewById(R.id.profilephoto_10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profilephoto_image.setImageResource(R.drawable.profilephoto10);
                editor_profilephoto.putInt(user_id,R.drawable.profilephoto10).commit();
                alert2.dismiss();
            }
        });
        view_custom2.findViewById(R.id.profilephoto_11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profilephoto_image.setImageResource(R.drawable.profilephoto11);
                editor_profilephoto.putInt(user_id,R.drawable.profilephoto11).commit();
                alert2.dismiss();
            }
        });
    }
}


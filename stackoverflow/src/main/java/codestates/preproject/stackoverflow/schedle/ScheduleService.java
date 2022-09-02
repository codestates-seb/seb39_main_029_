/*
package codestates.preproject.stackoverflow.schedle;

import codestates.preproject.stackoverflow.helper.email.repository.EmailRepository;
import codestates.preproject.stackoverflow.tags.Repository.TagRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduleService {

    private final EmailRepository emailRepository;

    public ScheduleService( EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    // [현재 날짜 및 시간을 확인하는 메소드]
    public static String getNowDateTime24() {

        long time = System.currentTimeMillis();
        SimpleDateFormat dayTime = new SimpleDateFormat("yyyy.MM.dd kk:mm:ss E요일");
        String str = dayTime.format(new Date(time));
        return str;
    }

    // [2초 후 3초마다 반복 실행 스케줄링]
    */
/*@Scheduled(fixedDelay = 3000,  initialDelay = 2000)
    public void threeRepeatJob(){
        System.out.println("\n");
        System.out.println("=======================================");
        System.out.println("[ScheduleService] : [threeRepeatJob] : [start]");
        System.out.println("[JopTime] : " + getNowDateTime24());
        System.out.println("=======================================");
        System.out.println("\n");
    }*//*


    */
/*//*
/ [1초 후 5초마다 반복 실행 스케줄링]
    @Scheduled(fixedDelay = 15000,  initialDelay = 1000)
    public void fiveRepeatJob(){
        System.out.println("hihihihihihihihihihihiihihihihihh");
        tagRepository.deleteAll();
    }*//*




    // [매일 오전 8시 58분에 실행되는 스케줄링]
    // [* 초(0-59)     * 분(0-59)　 　* 시간(0-23)　 　* 일(1-31)　　*　월(1-12)　　* 요일(0-7)]
    @Scheduled(cron = "0 58 4 * * *")
    public void everyDay_M_8_58_RepeatJob(){
        emailRepository.deleteAll();
    }

}*/

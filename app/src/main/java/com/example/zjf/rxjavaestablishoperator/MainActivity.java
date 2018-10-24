package com.example.zjf.rxjavaestablishoperator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
*@description  RxJava操作符中最常用的创建操作符:
 *                  1)create（）
 *                      作用：完整创建1个被观察者对象（Observable）。RxJava 中创建被观察者对象最基本的操作符
 *                   2)just（）
 *                      作用：快速创建1个被观察者对象（Observable）
 *                      发送事件的特点：直接发送 传入的事件
 *                      应用场景：快速创建 被观察者对象（Observable） & 发送10个以下事件
 *                  3)fromArray（）
 *                      作用:快速创建1个被观察者对象（Observable）
 *                      发送事件的特点：直接发送 传入的数组数据,会将数组中的数据转换为Observable对象
 *                      应用场景:快速创建 被观察者对象（Observable） & 发送10个以上事件（数组形式）
 *                               数组元素遍历
 *                  4)fromIterable（）
 *                      作用 :快速创建1个被观察者对象（Observable）
 *                      发送事件的特点：直接发送 传入的集合List数据 ,会将List中的数据转换为Observable对象
 *                      应用场景：快速创建 被观察者对象（Observable） & 发送10个以上事件（集合形式）
 *                                集合元素遍历
 *                  5)defer()
 *                      作用：直到有观察者（Observer ）订阅时，才动态创建被观察者对象（Observable） & 发送事件
 *                      应用场景：动态创建被观察者对象（Observable） & 获取最新的Observable对象数据
 *                  6）timer（）
 *                      作用：快速创建1个被观察者对象（Observable） 本质 = 延迟指定时间后，调用一次 onNext(0)
 *                      发送事件的特点：延迟指定时间后，发送1个数值0（Long类型）
 *                      应用场景：延迟指定事件，发送一个0，一般用于检测
 *                      注：timer操作符默认运行在一个新线程上
 *                      也可自定义线程调度器（第3个参数）：timer(long,TimeUnit,Scheduler)
 *                  7）interval（）
 *                      作用：快速创建1个被观察者对象（Observable） 发送的事件序列 = 从0开始、无限递增1的的整数序列
 *                      发送事件的特点：每隔指定时间 就发送 事件
 *                  8）intervalRange（）
 *                      作用：快速创建1个被观察者对象（Observable），作用类似于interval（），但可指定发送的数据的数量
 *                      发送事件的特点：每隔指定时间 就发送 事件，可指定发送的数据的数量
 *                  9）range()
 *                      作用：快速创建1个被观察者对象（Observable）,作用类似于intervalRange（），但区别在于：无延迟发送事件
 *                      发送事件的特点：连续发送 1个事件序列，可指定范围
 *
*@author zjf
*@date 2018/10/24 11:20
*/
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    Integer i = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*1、create()操作符*/
        /* // 1. 通过creat（）创建被观察者对象
        Observable.create(new ObservableOnSubscribe<Integer>() {
            // 2. 在复写的subscribe（）里定义需要发送的事件
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);

                emitter.onComplete();
            }// 至此，一个被观察者对象（Observable）就创建完毕
        }).subscribe(new Observer<Integer>() { // 3. 通过通过订阅（subscribe）连接观察者和被观察者
            
            // 4. 创建观察者 & 定义响应事件的行为
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "create操作符---开始采用subscribe连接");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "create操作符---接收到了事件"+ integer );
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "create操作符---对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "create操作符---对Complete事件作出响应");
            }
        });*/



        /*2、just()操作符*/
        // 1. 创建时传入整型1、2、3、4
        // 在创建后就会发送这些对象，相当于执行了onNext(1)、onNext(2)、onNext(3)、onNext(4)
        /*Observable.just(1,2,3,4,5).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "just操作符---开始采用subscribe连接");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "just操作符---接收到了事件"+ integer );
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "just操作符---对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "just操作符---对Complete事件作出响应");
            }
        });*/


        /*3、fromArray操作符*/
        // 1. 设置需要传入的数组
        /*Integer[] items = {1,2,3,4,5,6,7,8,9,10,11,12};
        // 2. 创建被观察者对象（Observable）时传入数组
        // 在创建后就会将该数组转换成Observable & 发送该对象中的所有数据
        Observable.fromArray(items).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "fromArray操作符---开始采用subscribe连接");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "fromArray操作符---接收到了事件"+ integer );
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "fromArray操作符---对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "fromArray操作符---对Complete事件作出响应");
            }
        });*/


        /*4、fromIterable（）*/
        // 1. 设置一个集合
        /*List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        // 2. 通过fromIterable()将集合中的对象 / 数据发送出去
        Observable.fromIterable(list).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "fromIterable操作符---开始采用subscribe连接");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "fromIterable操作符---接收到了事件"+ integer );
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "fromIterable操作符---对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "fromIterable操作符---对Complete事件作出响应");
            }
        });*/


        /*5、defer操作符*/
        // 2. 通过defer 定义被观察者对象
        // 注：此时被观察者对象还没创建
        /*Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
            @Override
            public ObservableSource<? extends Integer> call() throws Exception {
                return Observable.just(i);
            }
        });
        //第二次对i赋值
        i = 15;

        //观察者开始订阅
        // 注：此时，才会调用defer（）创建被观察者对象（Observable）
        observable.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "defer操作符---开始采用subscribe连接");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "defer操作符---接收到的整数是"+ integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "defer操作符---对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "defer操作符---对Complete事件作出响应");
            }
        });*/


        /*6、timer（）操作符*/
        /*Observable.timer(5, TimeUnit.SECONDS).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "defer操作符---开始采用subscribe连接");
            }

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "defer操作符---接收到的事件是"+ aLong);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "defer操作符---对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "defer操作符---对Complete事件作出响应");
            }
        });*/


        /*7、interval（）*/
        // 参数说明：参数1 = 第1次延迟时间；参数2 = 间隔时间数字；参数3 = 时间单位；
        // 该例子发送的事件序列特点：延迟3s后发送事件，每隔1秒产生1个数字（从0开始递增1，无限个）
        /*Observable.interval(3,1,TimeUnit.SECONDS).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "interval操作符---开始采用subscribe连接");
            }

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "interval操作符---接收到的事件是"+ aLong);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "interval操作符---对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "interval操作符---对Complete事件作出响应");
            }
        });*/

        /*8、intervalRange（）*/
        // 参数说明：参数1 = 事件序列起始点；参数2 = 事件数量；参数3 = 第1次事件延迟发送时间；
        // 参数4 = 间隔时间数字；参数5 = 时间单位
        // 1. 从3开始，一共发送10个事件；
        // 2. 第1次延迟2s发送，之后每隔2秒产生1个数字（从0开始递增1，无限个）
        /*Observable.intervalRange(3,10,2,1,TimeUnit.SECONDS).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "intervalRange操作符---开始采用subscribe连接");
            }

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "intervalRange操作符---接收到的事件是"+ aLong);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "intervalRange操作符---对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "intervalRange操作符---对Complete事件作出响应");
            }
        });*/



        /*9、Range()操作符*/
        // 参数说明：
        // 参数1 = 事件序列起始点；
        // 参数2 = 事件数量；
        // 注：若设置为负数，则会抛出异常
        Observable.range(3,10)
                // 该例子发送的事件序列特点：从3开始发送，每次发送事件递增1，一共发送10个事件
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "开始采用subscribe连接");
                    }
                    // 默认最先调用复写的 onSubscribe（）

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "接收到了事件"+ value  );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });
    }
}

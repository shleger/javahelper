package ru.saa.thread;

/**
 * Ctrl + Alt + L - форматирование кода. Можно в диалоговом окне установить пункт "организовать импорты", в таком случае будут отсортированы импорты, а так же убраны лишние.
 Ctrl + Alt + T - окружить блоком (try{}catch{}, while(){} etc.).
 Alt + Insert - генерация методов если был нажат в редакторе, если выделен каталог и была нажата эта комбинация, открывается содержимое пункта меню New для этого каталога
 ctrl+f12 -- список методов класса
 ctrl+/ или ctrl+shift+/ -- закомментировать/раскомментировать
 ctrl+alt+o -- приводит в порядок импорты
 Ctrl + Shift + Space - показывает лишь те варианты, которые подходят по типу
 Ctrl + Alt + B - показывает список дочерних классов выделенного класса
 Ctrl + Shift + A - список всех доступных команд в IDEA
 */

/**
 * Created with IntelliJ IDEA.
 * User: shleger
 * Date: 10.08.12
 * Time: 13:43
 * To change this template use File | Settings | File Templates.
 */
public class Task implements Runnable {
    private boolean isContinue = true;
    /**
     * delay time in millis
     */
    private Long delayTime;

    private Long currentTime = System.currentTimeMillis();

    @Override
    public void run() {

        while (isContinue &&  (System.currentTimeMillis() - currentTime) < delayTime) {

            try {
                Thread.sleep(500L);
                System.out.println("Process BIG data: " + (System.currentTimeMillis() - currentTime));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }


    }


    public Long getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(Long delayTime) {
        this.delayTime = delayTime;
    }

    public boolean isContinue() {
        return isContinue;
    }

    public void setContinue(boolean aContinue) {
        isContinue = aContinue;
    }
}

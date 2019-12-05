package 有限状态机;

public enum State1 {
    OFF {
        @Override
        void exit(Aircon1 ac) {
            super.exit(ac);
            startFan();
        }

        @Override
        void power(Aircon1 ac) {
            this.exit(ac);
            ac.state = FANONLY;
            ac.state.entry(ac);
        }

        @Override
        void cool(Aircon1 ac) {
            System.out.println("nothing");
        }
    },
    FANONLY {
        @Override
        void power(Aircon1 ac) {
            this.exit(ac);
            stopFan();
            ac.state = OFF;
            ac.state.entry(ac);
        }

        @Override
        void cool(Aircon1 ac) {
            this.exit(ac);
            ac.state = COOL;
            ac.state.entry(ac);
        }
    },
    COOL {
        @Override
        void exit(Aircon1 ac) {
            super.exit(ac);
            stopCool();
        }

        @Override
        void entry(Aircon1 ac) {
            startCool();
            super.entry(ac);
        }

        @Override
        void power(Aircon1 ac) {
            this.exit(ac);
            stopFan();
            ac.state = OFF;
            ac.state.entry(ac);
        }

        @Override
        void cool(Aircon1 ac) {
            this.exit(ac);
            ac.state = FANONLY;
            ac.state.entry(ac);
        }
    };

    //状态模式 提取的接口
    abstract void power(Aircon1 ac);

    abstract void cool(Aircon1 ac);

    //状态机的各种动作action methode
    void entry(Aircon1 ac) {
        System.out.println("→" + ac.state.name());
    }

    void exit(Aircon1 ac) {
        System.out.println(ac.state.name() + "→ ");
    }

    void startCool() {
        System.out.println("start Cool");
    }

    void stopCool() {
        System.out.println("stop Cool");
    }

    void startFan() {
        System.out.println("start Fan");
    }

    void stopFan() {
        System.out.println("stop Fan");
    }
}
'use stric';

class DateUtils {
    static timeDifference(time) {
        var initialTime = moment(time);

        var diff = moment().diff(initialTime, 'days');
        if(diff > 14) {
            return initialTime.format("DD/MM/YYYY HH:mm:ss");
        } else {
            return initialTime.fromNow();
        }
    }
}

export default DateUtils;

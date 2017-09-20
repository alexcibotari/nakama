import {DateAdapter} from '@angular/material';
import * as moment from 'moment';
import {isMoment, Moment} from 'moment';

/** The default date names to use if Intl API is not available. */
const DEFAULT_DATE_NAMES = range(31, i => String(i + 1));

/** Creates an array and fills it with values. */
function range<T>(length: number, valueFunction: (index: number) => T): T[] {
    const valuesArray = Array(length);
    for (let i = 0; i < length; i++) {
        valuesArray[i] = valueFunction(i);
    }
    return valuesArray;
}

export class MomentDateAdapter extends DateAdapter<Moment> {

    private localeData = moment.localeData();

    getYear(date: Moment): number {
        return date.year();
    }

    getMonth(date: Moment): number {
        return date.month();
    }

    getDate(date: Moment): number {
        return date.date();
    }

    getDayOfWeek(date: Moment): number {
        return date.day();
    }

    getMonthNames(style: 'long' | 'short' | 'narrow'): string[] {
        switch (style) {
            case 'long':
                return this.localeData.months();
            case 'short':
                return this.localeData.monthsShort();
            case 'narrow':
                return this.localeData.monthsShort().map(month => month[0]);
        }
    }

    getDateNames(): string[] {
        return DEFAULT_DATE_NAMES;
    }

    getDayOfWeekNames(style: 'long' | 'short' | 'narrow'): string[] {
        switch (style) {
            case 'long':
                return this.localeData.weekdays();
            case 'short':
                return this.localeData.weekdaysShort();
            case 'narrow':
                // Moment does not accept format even though @types/moment suggests it does
                return this.localeData.weekdaysShort();
        }
    }

    getYearName(date: Moment): string {
        return String(date.year());
    }

    getFirstDayOfWeek(): number {
        return this.localeData.firstDayOfWeek();
    }

    getNumDaysInMonth(date: Moment): number {
        return date.daysInMonth();
    }

    clone(date: Moment): Moment {
        return date.clone();
    }

    createDate(year: number, month: number, date: number): Moment {
        return moment([year, month, date]);
    }

    today(): Moment {
        return moment();
    }

    parse(value: any, parseFormat: any): Moment {
        return moment(value, parseFormat);
    }

    format(date: Moment, displayFormat: any): string {
        if (date) {
            return date.format(displayFormat);
        }
        else {
            return '';
        }
    }

    addCalendarYears(date: Moment, years: number): Moment {
        return date.clone().add(years, 'y');
    }

    addCalendarMonths(date: Moment, months: number): Moment {
        return date.clone().add(months, 'M');
    }

    addCalendarDays(date: Moment, days: number): Moment {
        return date.clone().add(days, 'd');
    }

    getISODateString(date: Moment): string {
        return date.toISOString();
    }

    isDateInstance(obj: any): boolean {
        console.log('isDateInstance : '+ (obj instanceof moment) + ' ' + obj);
        return obj instanceof moment;
    }

    isValid(date: Moment): boolean {
        console.log('isValid : '+ date);
        return date.isValid();
    }

    setLocale(locale: any): void {
        console.info('setLocale', locale);
        this.localeData = moment.localeData(locale);
    }

    compareDate(first: Moment, second: Moment): number {
        return first.diff(second, 'seconds', true);
    }

    sameDate(first: any | Moment, second: any | Moment): boolean {
        if (first == null) {
            // same if both null
            return second == null;
        }
        else if (isMoment(first)) {
            return first.isSame(second);
        }
        else {
            const isSame = super.sameDate(first, second);
            console.warn('first not a Moment. fallback to super.sameDate()', first, second, isSame);
            return isSame;
        }
    }

    clampDate(date: Moment, min?: any | Moment, max?: any | Moment): Moment {
        if (min && date.isBefore(min)) {
            return min;
        }
        else if (max && date.isAfter(max)) {
            return max;
        }
        else {
            return date;
        }
    }
}

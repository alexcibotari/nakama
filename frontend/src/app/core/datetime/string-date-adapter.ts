import {DateAdapter} from "@angular/material";
import * as moment from "moment";
import {isMoment, Moment} from "moment";

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

export class StringDateAdapter extends DateAdapter<string> {

    private localeData = moment.localeData();

    getYear(date: string): number {
        return moment(date).year();
    }

    getMonth(date: string): number {
        return moment(date).month();
    }

    getDate(date: string): number {
        return moment(date).date();
    }

    getDayOfWeek(date: string): number {
        return moment(date).day();
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

    getYearName(date: string): string {
        return String(moment(date).year());
    }

    getFirstDayOfWeek(): number {
        return this.localeData.firstDayOfWeek();
    }

    getNumDaysInMonth(date: string): number {
        return moment(date).daysInMonth();
    }

    clone(date: string): string {
        return moment(date).clone().toString();
    }

    createDate(year: number, month: number, date: number): string {
        return moment([year, month, date]).format();
    }

    today(): string {
        return moment().format();
    }

    parse(value: any, parseFormat: any): string {
        return moment(value, parseFormat).format();
    }

    format(date: string, displayFormat: any): string {
        if (date) {
            return moment(date).format(displayFormat);
        }
        else {
            return '';
        }

    }

    addCalendarYears(date: string, years: number): string {
        return moment(date).clone().add(years, 'y').format();
    }

    addCalendarMonths(date: string, months: number): string {
        return moment(date).clone().add(months, 'M').format();
    }

    addCalendarDays(date: string, days: number): string {
        return moment(date).clone().add(days, 'd').format();
    }

    getISODateString(date: string): string {
        return moment(date).toISOString();
    }

    isDateInstance(obj: any): boolean {
        return typeof obj === 'string';
    }

    isValid(date: string): boolean {
        return date != null && date.length === 10;
    }

    setLocale(locale: any): void {
        console.info('setLocale', locale);
        this.localeData = moment.localeData(locale);
    }

    compareDate(first: string, second: string): number {
        return moment(first).diff(moment(second), 'seconds', true);
    }

    sameDate(first: any | string, second: any | string): boolean {
        if (first == null) {
            // same if both null
            return second == null;
        }
        else if (isMoment(moment(first))) {
            return moment(first).isSame(moment(second));
        }
        else {
            const isSame = super.sameDate(first, second);
            console.warn('first not a Moment. fallback to super.sameDate()', first, second, isSame);
            return isSame;
        }
    }

    clampDate(date: string, min?: any | string, max?: any | string): string {
        if (moment(min) && moment(date).isBefore(moment(min))) {
            return moment(min).format();
        }
        else if (moment(max) && moment(date).isAfter(moment(max))) {
            return moment(max).format();
        }
        else {
            return moment(date).format();
        }
    }
}

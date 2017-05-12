import {Propery} from './propery.model';
import {ResourceLinks} from './resource-links.model';
import {Resource} from './resource.model';


/**
 * Abstraction of a HAL resource.
 *
 */
export class Resources<T extends Resource> {

    /**
     * The reserved "_links" property is OPTIONAL.
     *
     * It is an object whose property names are link relation types (as
     * defined by [RFC5988]) and values are either a Link Object or an array
     * of Link Objects.  The subject resource of these links is the Resource
     * Object of which the containing "_links" object is a property.
     */
    _links: ResourceLinks;

    /**
     * The reserved "_embedded" property is OPTIONAL
     *
     * It is an object whose property names are link relation types (as
     * defined by [RFC5988]) and values are either a Resource Object or an
     * array of Resource Objects.
     *
     * Embedded Resources MAY be a full, partial, or inconsistent version of
     * the representation served from the target URI.
     */
    _embedded?: Propery<T[]>;


    /**
     * In addition to the methods mentioned here, resource has all properties of
     * the source object. This is also true for embedded Resource objects. The
     * non-HAL properties (that is, any property except _links and _embedded) are
     * copied over to the Resource object. This is always a shallow copy, so
     * modifying the a non-HAL property in the Resource object might also alter
     * the source object and vice versa.
     *
     * The Resource object also has the properties _links and _embedded but they
     * might differ from the _links/_embedded properties in the source object
     * (Halfred applies some normalization to them). These are not intended to be
     * accessed by clients directly, instead, use the provided methods to work
     * with links and embedded resources.
     */
    [key: string]: any;

}


/**
 * Abstraction of a HAL resource.
 *
 */
export abstract class Resource {

    /**
     * The reserved "_links" property is OPTIONAL.
     *
     * It is an object whose property names are link relation types (as
     * defined by [RFC5988]) and values are either a Link Object or an array
     * of Link Objects.  The subject resource of these links is the Resource
     * Object of which the containing "_links" object is a property.
     */
    _links: LinkRelations;

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
    _embedded?: Relations<Resource | Resource[]>;

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
    _links: LinkRelations;

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
    _embedded?: Relations<T[]>;

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

export interface LinkRelations extends Relations<Link>{

    /**
     * Each Resource Object SHOULD contain a 'self' link that corresponds
     * with the IANA registered 'self' relation (as defined by [RFC5988])
     * whose target is the resource's URI.
     */
    self: Link;

    next?: Link;
    prev?: Link;
    first?: Link;
    last?: Link;
}

/**
 * A Link Object represents a hyperlink from the containing resource to a URI.
 *
 */
export interface Link {

    /**
     * The "href" property is REQUIRED.
     *
     * Its value is either a URI [RFC3986] or a URI Template [RFC6570].
     *
     * If the value is a URI Template then the Link Object SHOULD have a
     * "templated" attribute whose value is true.
     */
    href: string;

    /**
     * The "templated" property is OPTIONAL.
     *
     * Its value is boolean and SHOULD be true when the Link Object's "href"
     * property is a URI Template.
     *
     * Its value SHOULD be considered false if it is undefined or any other
     * value than true.
     */
    templated?: boolean;

    /**
     * The "type" property is OPTIONAL.
     *
     * Its value is a string used as a hint to indicate the media type
     * expected when dereferencing the target resource.
     */
    type?: string;

    /**
     * The "deprecation" property is OPTIONAL.
     *
     * Its presence indicates that the link is to be deprecated (i.e.
     * removed) at a future date.  Its value is a URL that SHOULD provide
     * further information about the deprecation.
     *
     * A client SHOULD provide some notification (for example, by logging a
     * warning message) whenever it traverses over a link that has this
     * property.  The notification SHOULD include the deprecation property's
     * value so that a client manitainer can easily find information about
     * the deprecation.
     */
    deprecation?: string;

    /**
     * The "name" property is OPTIONAL.
     *
     * Its value MAY be used as a secondary key for selecting Link Objects
     * which share the same relation type.
     */
    name?: string;

    /**
     * The "profile" property is OPTIONAL.
     *
     * Its value is a string which is a URI that hints about the profile (as
     * defined by [I-D.wilde-profile-link]) of the target resource.
     */
    profile?: string;

    /**
     * The "title" property is OPTIONAL.
     *
     * Its value is a string and is intended for labelling the link with a
     * human-readable identifier (as defined by [RFC5988]).
     */
    title?: string;

    /**
     * The "hreflang" property is OPTIONAL.
     *
     * Its value is a string and is intended for indicating the language of
     * the target resource (as defined by [RFC5988]).
     */
    hreflang?: string;
}

export interface Relations<T> {
    [key: string]: T | any;
}

import { Link } from './link.model';
import {Propery} from './propery.model';

export interface ResourceLinks extends Propery<Link>{

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

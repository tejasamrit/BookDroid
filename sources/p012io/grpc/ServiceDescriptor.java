package p012io.grpc;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import javax.annotation.Nullable;

/* renamed from: io.grpc.ServiceDescriptor */
public final class ServiceDescriptor {
    private final Collection<MethodDescriptor<?, ?>> methods;
    private final String name;
    private final Object schemaDescriptor;

    public ServiceDescriptor(String str, MethodDescriptor<?, ?>... methodDescriptorArr) {
        this(str, (Collection<MethodDescriptor<?, ?>>) Arrays.asList(methodDescriptorArr));
    }

    public ServiceDescriptor(String str, Collection<MethodDescriptor<?, ?>> collection) {
        this(newBuilder(str).addAllMethods((Collection) Preconditions.checkNotNull(collection, "methods")));
    }

    private ServiceDescriptor(Builder builder) {
        String access$100 = builder.name;
        this.name = access$100;
        validateMethodNames(access$100, builder.methods);
        this.methods = Collections.unmodifiableList(new ArrayList(builder.methods));
        this.schemaDescriptor = builder.schemaDescriptor;
    }

    public String getName() {
        return this.name;
    }

    public Collection<MethodDescriptor<?, ?>> getMethods() {
        return this.methods;
    }

    @Nullable
    public Object getSchemaDescriptor() {
        return this.schemaDescriptor;
    }

    static void validateMethodNames(String str, Collection<MethodDescriptor<?, ?>> collection) {
        HashSet hashSet = new HashSet(collection.size());
        for (MethodDescriptor next : collection) {
            Preconditions.checkNotNull(next, "method");
            String serviceName = next.getServiceName();
            Preconditions.checkArgument(str.equals(serviceName), "service names %s != %s", (Object) serviceName, (Object) str);
            Preconditions.checkArgument(hashSet.add(next.getFullMethodName()), "duplicate name %s", (Object) next.getFullMethodName());
        }
    }

    public static Builder newBuilder(String str) {
        return new Builder(str);
    }

    /* renamed from: io.grpc.ServiceDescriptor$Builder */
    public static final class Builder {
        /* access modifiers changed from: private */
        public List<MethodDescriptor<?, ?>> methods;
        /* access modifiers changed from: private */
        public String name;
        /* access modifiers changed from: private */
        public Object schemaDescriptor;

        private Builder(String str) {
            this.methods = new ArrayList();
            setName(str);
        }

        public Builder setName(String str) {
            this.name = (String) Preconditions.checkNotNull(str, "name");
            return this;
        }

        public Builder addMethod(MethodDescriptor<?, ?> methodDescriptor) {
            this.methods.add(Preconditions.checkNotNull(methodDescriptor, "method"));
            return this;
        }

        /* access modifiers changed from: private */
        public Builder addAllMethods(Collection<MethodDescriptor<?, ?>> collection) {
            this.methods.addAll(collection);
            return this;
        }

        public Builder setSchemaDescriptor(@Nullable Object obj) {
            this.schemaDescriptor = obj;
            return this;
        }

        public ServiceDescriptor build() {
            return new ServiceDescriptor(this);
        }
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("name", (Object) this.name).add("schemaDescriptor", this.schemaDescriptor).add("methods", (Object) this.methods).omitNullValues().toString();
    }
}
